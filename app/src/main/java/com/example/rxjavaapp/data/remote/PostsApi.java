package com.example.rxjavaapp.data.remote;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.model.PostDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PostsApi {

    @GET("posts")
    Single<List<PostDTO>> getPosts();
}
