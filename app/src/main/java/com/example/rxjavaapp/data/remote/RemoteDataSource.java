package com.example.rxjavaapp.data.remote;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.model.PostDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RemoteDataSource {
    private PostsService postsService;

    public RemoteDataSource(PostsService postsService) {
        this.postsService = postsService;
    }

    public Single<List<PostDTO>> getPosts(){
        return postsService.getAllPosts();
    }
}
