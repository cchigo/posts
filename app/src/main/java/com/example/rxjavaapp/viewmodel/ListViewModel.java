package com.example.rxjavaapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjavaapp.data.model.mapper.DTOModelMapper;
import com.example.rxjavaapp.data.model.mapper.EntityModelMapper;
import com.example.rxjavaapp.data.repository.DataRepository;
import com.example.rxjavaapp.data.local.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class ListViewModel extends ViewModel {
    public MutableLiveData<Map<String, ArrayList<Post>>> _posts = new MutableLiveData();
    public LiveData<Map<String, ArrayList<Post>>> posts = _posts;

    public MutableLiveData<List<Post>> _userPosts = new MutableLiveData();
    public LiveData<List<Post>> userPosts = _userPosts;

    public MutableLiveData<List<Post>> _favouritePosts = new MutableLiveData();
    public LiveData<List<Post>> favouritePosts = _favouritePosts;

    public MutableLiveData<Boolean> loadingError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private DataRepository dataRepository;
    private EntityModelMapper localMapper;
    private DTOModelMapper dtoMapper;

    private MutableLiveData<List<Post>> _newPost = new MutableLiveData();
    public LiveData<List<Post>> newPost = _newPost;


    public @Inject
    ListViewModel(DataRepository dataRepository,
                  EntityModelMapper entityModelMapper,
                  DTOModelMapper dtoModelMapper) {
        this.dataRepository = dataRepository;
        this.localMapper = entityModelMapper;
        this.dtoMapper = dtoModelMapper;

        updateDatabase();
        getLocalData();
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        updateDatabase();
    }

    //Get from local database
    public void getLocalData() {
        disposable.add(dataRepository.getLocalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(p -> loading.setValue(true))
                .subscribe(
                        result -> _posts.setValue(groupedList(localMapper.fromList(result))),
                        error -> {
                        }
                ));
    }

    //Get from remote
    public void updateDatabase() {
        disposable.add(dataRepository.getRemoteData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(p -> loading.setValue(true))
                .subscribe(
                        result -> {
                            loading.setValue(false);
                            dataRepository.saveLocalData(
                                    localMapper.toList(dtoMapper.fromList(result)));
                        },
                        error -> {
                            loading.setValue(false);
                        }
                ));
    }

    public void getPostsByUserId(String userId) {
        disposable.add(
                dataRepository.getPostByUserId(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(p -> loading.setValue(true))
                        .subscribe(
                                result -> {
                                    loading.setValue(false);
                                    _userPosts.setValue(localMapper.fromList(result));
                                },
                                error -> {
                                    loading.setValue(false);
                                })
        );
    }

    public void getFavouritePost(Boolean isFavourite) {
        disposable.add(dataRepository.getFavouritePosts(isFavourite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(p -> loading.setValue(true))
                .subscribe(
                        result -> {
                            loading.setValue(false);
                            _favouritePosts.setValue(localMapper.fromList(result));
                        },
                        error -> {
                            loading.setValue(false);
                        }
                ));
    }

    public void setFavouritePost(Post post) {
        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dataRepository.setFavourite(localMapper.to(post));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    private Map<String, ArrayList<Post>> groupedList(List<Post> posts) {
        Map<String, ArrayList<Post>> hashmap = new HashMap<>();
        for (Post post : posts) {
            String key = post.getUserId();

            if (hashmap.containsKey(key)) {
                hashmap.get(key).add(post);
            } else {
                ArrayList<Post> newList = new ArrayList();
                newList.add(post);
                hashmap.put(key, newList);
            }
        }
        return hashmap;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
