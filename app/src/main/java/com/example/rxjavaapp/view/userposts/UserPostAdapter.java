package com.example.rxjavaapp.view.userposts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.databinding.UserPostsItemBinding;
import com.example.rxjavaapp.model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.UserPostsViewHolder> {
    private ArrayList<PostModel> postItem;

    public UserPostAdapter(ArrayList<PostModel> postItem) {
        this.postItem = postItem;
    }

    class UserPostsViewHolder extends RecyclerView.ViewHolder {

        private UserPostsItemBinding userPostItemBinding;

        public UserPostsViewHolder(UserPostsItemBinding binding) {

            super(binding.getRoot());
            userPostItemBinding = binding;

        }

        void bind(PostModel postModel) {
           userPostItemBinding.userIdText.setText(postModel.getUserId());
            userPostItemBinding.postTitleText.setText(postModel.getTitle());
            userPostItemBinding.postBodyText.setText(postModel.getBody());
            userPostItemBinding.postItemLayout.setOnClickListener(v -> {
             //   listener.onItemClick(postItem);
            });
        }
    }

    @NonNull
    @Override
    public UserPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserPostAdapter.UserPostsViewHolder(UserPostsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserPostsViewHolder holder, int position) {
     PostModel post = postItem.get(position);
        holder.bind(post);
    }



    @Override
    public int getItemCount() {
        return postItem.size();
    }
}
