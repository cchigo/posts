package com.example.rxjavaapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PostsApi {

    @GET("posts")
    Single<List<PostModel>> getPosts();

    @GET("posts")
    Single<Object> getPosts2();
}
