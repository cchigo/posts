package com.example.rxjavaapp.data.model.mapper;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.data.local.Post;

import java.util.ArrayList;
import java.util.List;

public class EntityModelMapper implements BaseModelMapper<Post, PostEntity> {


    @Override
    public Post from(PostEntity data) {
        return new Post(
                data.getUserId(),
                data.getId(),
                data.getTitle(),
                data.getBody(),
                data.getFavourite());
    }

    @Override
    public PostEntity to(Post data) {
        return new PostEntity(data.getUserId(),
                data.getId(),
                data.getTitle(),
                data.getBody(),
                false);

    }
    public List<Post> fromList(List<PostEntity> data){
        List<Post> result = new ArrayList();
        for (PostEntity postEntity: data) {
            result.add(from(postEntity));
        }
        return result;
    }
    public List<PostEntity> toList(List<Post> data){
        List<PostEntity> result = new ArrayList();
        for (Post post : data) {
            result.add(to(post));
        }
        return result;
    }



}
