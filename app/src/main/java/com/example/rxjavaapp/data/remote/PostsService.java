package com.example.rxjavaapp.data.remote;

import com.example.rxjavaapp.di.DaggerApiComponent;
import com.example.rxjavaapp.model.PostModel;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class PostsService {

    private static PostsService instance;

    @Inject
    public PostsApi api;

    private PostsService() {
        DaggerApiComponent.create().inject(this);
    }

    public static PostsService getInstance() {
        if (instance == null) {
            instance = new PostsService();
        }
        return instance;
    }

    public Single<List<PostModel>> getAllPosts() {
        return api.getPosts();
    }

}
