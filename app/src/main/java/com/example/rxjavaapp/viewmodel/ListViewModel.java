package com.example.rxjavaapp.viewmodel;

import android.app.Application;

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

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<Map<String, ArrayList<PostModel>>> _countries = new MutableLiveData();
    public LiveData<Map<String, ArrayList<PostModel>>> countries = _countries;

    public MutableLiveData<List<Post>> _postWithFavourite = new MutableLiveData();
    public LiveData<List<Post>> postWithFavourite = _postWithFavourite;

    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
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
        postsDAO = appDatabase.getPostsDAO();
        appDBHelper = new AppDBHelperImpl(postsDAO);
        DaggerApiComponent.create().inject(this);
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
                                _countries.setValue(groupedList(postModel));
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                                List<Post> copy = new ArrayList();
                                for (PostModel post : postModel) {
                                    copy.add(new Post(0,
                                            post.getUserId(),
                                            post.getId(),
                                            post.getTitle(),
                                            post.getBody()));
                                }

                                saveToLocalDb(copy);
                            }

                            @Override
                            public void onError(Throwable e) {
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }

    private void saveToLocalDb(List<Post> posts) {

        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                appDBHelper.insertAllPosts(posts);
            }
        })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe();
    }

    private void addToFavouriteDB() {
    }
//private void

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
