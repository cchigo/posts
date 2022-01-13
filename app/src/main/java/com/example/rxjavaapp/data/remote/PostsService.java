package com.example.rxjavaapp.data.remote;
import com.example.rxjavaapp.model.PostDTO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PostsService {

    public PostsApi api;

    public @Inject PostsService(PostsApi api) {
        this.api = api;
    }

    public Single<List<PostDTO>> getAllPosts() {
        return api.getPosts();
    }

}
