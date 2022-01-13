package com.example.rxjavaapp.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class AppDBHelperImpl implements AppDbHelper {

    PostsDAO postsDAO;

    public AppDBHelperImpl(PostsDAO postsDAO) {
        this.postsDAO = postsDAO;
    }

    @Override
    public void insertAllPosts(List<PostEntity> posts) {
        postsDAO.insertAll(posts);
    }

    @Override
    public void insertPost(PostEntity post) {
        postsDAO.addFavouritePost(post);
    }

    @Override
    public void deletePost(PostEntity post) {
        postsDAO.deletePost(post);
    }

    @Override
    public Flowable<List<PostEntity>> getPostsFromLocal() {
        return null;
    }

    @Override
    public void updatePost(PostEntity post) {
        postsDAO.changeFavourite(post);
    }
}
