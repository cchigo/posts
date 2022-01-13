package com.example.rxjavaapp.data.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Single;;

public class RemoteDataSource {
    private PostsService postsService;

    public RemoteDataSource(PostsService postsService) {
        this.postsService = postsService;
    }

    public Single<List<PostDTO>> getPosts(){
        return postsService.getAllPosts();
    }
}
