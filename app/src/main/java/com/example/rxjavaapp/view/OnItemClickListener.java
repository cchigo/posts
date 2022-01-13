package com.example.rxjavaapp.view;


import com.example.rxjavaapp.data.local.Post;

public interface OnItemClickListener {
    void onItemClick(String userId);
    void onFavouriteClick(Post post);
}
