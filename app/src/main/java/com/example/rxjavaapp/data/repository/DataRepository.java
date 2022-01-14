package com.example.rxjavaapp.data.repository;

import com.example.rxjavaapp.data.local.LocalDataSource;
import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.data.model.mapper.DTOModelMapper;
import com.example.rxjavaapp.data.model.mapper.EntityModelMapper;
import com.example.rxjavaapp.data.remote.RemoteDataSource;
import com.example.rxjavaapp.data.remote.PostDTO;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class DataRepository implements Repository {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;
    private EntityModelMapper localMapper;
    private DTOModelMapper dtoMapper;

    public DataRepository(LocalDataSource localDataSource,
                          RemoteDataSource remoteDataSource,
                          EntityModelMapper localMapper,
                          DTOModelMapper dtoMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.localMapper = localMapper;
        this.dtoMapper = dtoMapper;
    }
    @Override
    public Flowable<List<PostEntity>> getLocalData(){
        return localDataSource.getPosts();
    }

    public @NonNull Single<List<PostDTO>> getRemoteData(){
        return  remoteDataSource.getPosts();

    }
    @Override
    public void saveLocalData(List<PostEntity> posts){
        localDataSource.savePosts(posts);
    }
    @Override
    public Flowable<List<PostEntity>> getPostByUserId(String userId) {
        return localDataSource.getPostsByUserId(userId);
    }
    @Override
    public Flowable<List<PostEntity>> getFavouritePosts(Boolean isFavourite) {
        return localDataSource.getFavouritePosts(isFavourite);
    }

    @Override
    public void setFavourite(List<PostEntity> postEntity) {
        localDataSource.setFavourite(postEntity);
    }

}
