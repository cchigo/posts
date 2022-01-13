package com.example.rxjavaapp.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import lombok.AllArgsConstructor;
import lombok.Data;

public class LocalDataSource {
    private AppDatabase database;

    public LocalDataSource(AppDatabase database) {
        this.database = database;
    }

    public Flowable<List<PostEntity>> getPosts() {
        return database.getPostsDAO().getAllPosts();
    }
    public void savePosts(List<PostEntity> posts){
       database.getPostsDAO().insertAll(posts);
    }



}
