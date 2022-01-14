package com.example.rxjavaapp.view;


import com.example.rxjavaapp.data.local.Post;

import java.util.List;

public interface OnItemClickListener {
    void onItemClick(String userId);
    void onFavouriteClick(List<Post> post);
}
