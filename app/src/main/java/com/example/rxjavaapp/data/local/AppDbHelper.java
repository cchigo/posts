package com.example.rxjavaapp.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface AppDbHelper {

    public  void insertAllPosts(List<Post> post);

    public  void insertPost(Post post);

    public  void deletePost(Post post);

    public Flowable<List<Post>> getAllPost();

    public  void updatePost(Post post);

}
