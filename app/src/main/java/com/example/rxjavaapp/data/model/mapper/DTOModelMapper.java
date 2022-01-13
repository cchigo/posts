package com.example.rxjavaapp.data.model.mapper;
import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.model.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class DTOModelMapper implements BaseModelMapper<Post, PostDTO>{

    @Override
    public Post from(PostDTO data) {
        return new Post(
                data.getUserId(),
                data.getId(),
                data.getTitle(),
                data.getBody());
    }

    @Override
    public PostDTO to(Post data) {
        return new PostDTO(
                data.getUserId(),
                data.getId(),
                data.getTitle(),
                data.getBody());
    }

    public List<Post> fromList(List<PostDTO> data){
        List<Post> result = new ArrayList();
        for (PostDTO dto: data) {
            result.add(from(dto));
        }
        return result;
    }
    public List<PostDTO> toList(List<Post> data){
        List<PostDTO> result = new ArrayList();
        for (Post post : data) {
            result.add(to(post));
        }
        return result;
    }
}
