package com.example.rxjavaapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rxjavaapp.data.local.AppDBHelperImpl;
import com.example.rxjavaapp.data.local.AppDatabase;
import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.data.local.PostsDAO;
import com.example.rxjavaapp.di.DaggerApiComponent;
import com.example.rxjavaapp.model.PostModel;
import com.example.rxjavaapp.data.remote.PostsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListViewModel extends AndroidViewModel {
    //   private PostsRepository postsRepository;
    public MutableLiveData<Map<String, ArrayList<PostModel>>> _posts = new MutableLiveData();
    public LiveData<Map<String, ArrayList<PostModel>>> posts = _posts;

    public MutableLiveData<List<Post>> _userPostsLocal = new MutableLiveData();
    public LiveData<List<Post>> userPostsLocal = _userPostsLocal;

    public MutableLiveData<ArrayList<PostModel>> _userPosts = new MutableLiveData();
    public LiveData<ArrayList<PostModel>> userPosts = _userPosts;

    public MutableLiveData<Boolean> loadingError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    Application application;
    AppDatabase appDatabase;
    PostsDAO postsDAO;
    AppDBHelperImpl appDBHelper;


    @Inject
    public PostsService postsService;

    public ListViewModel(Application application) {
        super(application);
        this.application = application;

        appDatabase = AppDatabase.getInstance(application);
        //    postsRepository=new PostsRepository(application);
        postsDAO = appDatabase.getPostsDAO();
        appDBHelper = new AppDBHelperImpl(postsDAO);
        DaggerApiComponent.create().inject(this);
        fetchPosts();
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        fetchPosts();
    }

    private Map<String, ArrayList<PostModel>> groupedList(List<PostModel> posts) {
        Map<String, ArrayList<PostModel>> hashmap = new HashMap<>();
        for (PostModel post : posts) {
            String key = post.getUserId();
            if (hashmap.containsKey(key)) {
                hashmap.get(key).add(post);
            } else {
                ArrayList<PostModel> newList = new ArrayList();
                newList.add(post);
                hashmap.put(key, newList);
            }
        }
        return hashmap;
    }

    private void fetchPosts() {
        loading.setValue(true);
        disposable.add(
                postsService.getAllPosts()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<PostModel>>() {
                            @Override
                            public void onSuccess(List<PostModel> postModel) {
                                _posts.setValue(groupedList(postModel));
                                loadingError.setValue(false);
                                loading.setValue(false);
                                List<Post> copy = new ArrayList();
                                for (PostModel post : postModel) {
                                    copy.add(new Post(0,
                                                    post.getUserId(),
                                                    post.getId(),
                                                    post.getTitle(),
                                                    post.getBody(),
                                                    false
                                            )
                                    );
                                }
                                saveToLocalDb(copy);
                            }

                            @Override
                            public void onError(Throwable e) {
                                loadingError.setValue(false);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }

    private void saveToLocalDb(List<Post> posts) {
// check if room db is null; before using the values
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                appDBHelper.insertAllPosts(posts);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void readFromLocal() {
        disposable.add(appDBHelper.getPostsFromLocal()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                               @Override
                               public void accept(List<Post> posts) throws Throwable {
                                   _userPostsLocal.setValue(posts);

                               }
                           }
                )
        );
    }

    private void updateFavourite(Post post) {
//        appDBHelper.updatePost(post)
        disposable.add( Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                appDBHelper.updatePost(post);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                  //change to favourite
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(application.getApplicationContext()," error occurred ", Toast.LENGTH_LONG).show();

                    }
                }));
    }

//private void

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
