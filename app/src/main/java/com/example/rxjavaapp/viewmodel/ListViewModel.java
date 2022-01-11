package com.example.rxjavaapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjavaapp.di.DaggerApiComponent;
import com.example.rxjavaapp.model.PostModel;
import com.example.rxjavaapp.data.remote.PostsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListViewModel extends ViewModel {

    public MutableLiveData<Map<String, ArrayList<PostModel>>> _countries = new MutableLiveData();
    public LiveData<Map<String, ArrayList<PostModel>>> countries = _countries;

    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    public PostsService postsService;

    public ListViewModel(){
        super();
        DaggerApiComponent.create().inject(this);
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        fetchPosts();
    }

    private Map<String, ArrayList<PostModel>> groupedList(List<PostModel> countries){
        Map<String, ArrayList<PostModel>> hashmap = new HashMap<>();
        for (PostModel country: countries){
            String key = country.getUserId();
            if(hashmap.containsKey(key)){
                hashmap.get(key).add(country);
            }else{
                ArrayList<PostModel> newList = new ArrayList();
                newList.add(country);
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
    private void saveToLocalDb(){}

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
