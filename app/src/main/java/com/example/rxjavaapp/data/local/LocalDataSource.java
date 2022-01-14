package com.example.rxjavaapp.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalDataSource {
    private AppDatabase database;

    public LocalDataSource(AppDatabase database) {
        this.database = database;
    }

    public Flowable<List<PostEntity>> getPosts() {
        return database.getPostsDAO().getAllPosts();
    }
    public Flowable<List<PostEntity>> getPostsByUserId(String userId) {
        return database.getPostsDAO().getPostsByUserId(userId);
    }
    public Flowable<List<PostEntity>> getFavouritePosts(Boolean isFavourite) {
        return database.getPostsDAO().getFavourites(isFavourite);
    }
    public void setFavourite(List<PostEntity> postEntity) {
        database.getPostsDAO().updateFavourite(postEntity);
    }

    public void savePosts(List<PostEntity> posts){
       database.getPostsDAO().insertAll(posts);
    }



}
