package com.example.rxjavaapp.data.local;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class AppDBHelperImpl implements AppDbHelper {

    PostsDAO postsDAO;

    public AppDBHelperImpl(PostsDAO postsDAO) {
        this.postsDAO = postsDAO;
    }

    @Override
    public void insertAllPosts(List<Post> posts) {
        postsDAO.insertAll(posts);
    }

    @Override
    public void insertPost(Post post) {
        postsDAO.addFavouritePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postsDAO.deletePost(post);
    }

    @Override
    public Flowable<List<Post>> getAllPost() {
         return postsDAO.getAllPosts();
    }

    @Override
    public void updatePost(Post post) {
        postsDAO.changeFavourite(post);
    }
}
