package com.example.rxjavaapp.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface AppDbHelper {

    public  void insertAllPosts(List<PostEntity> post);

    public  void insertPost(PostEntity post);

    public  void deletePost(PostEntity post);

    public Flowable<List<PostEntity>> getPostsFromLocal();

    public  void updatePost(PostEntity post);

}
