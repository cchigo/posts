package com.example.rxjavaapp.data.repository;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.model.PostDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface Repository {

    Flowable<List<PostEntity>> getLocalData();

    Single<List<PostDTO>> getRemoteData();

    void saveLocalData(List<PostEntity> posts);

}
