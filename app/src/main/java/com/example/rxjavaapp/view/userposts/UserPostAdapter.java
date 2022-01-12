package com.example.rxjavaapp.view.userposts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.data.local.PostItem;
import com.example.rxjavaapp.databinding.UserPostsItemBinding;
import com.example.rxjavaapp.model.PostModel;

import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.UserPostsViewHolder> {
    private PostItem postItem;

    public UserPostAdapter(PostItem posts) {
        this.postItem = posts;
    }

    class UserPostsViewHolder extends RecyclerView.ViewHolder {

        private UserPostsItemBinding userPostItemBinding;

        public UserPostsViewHolder(UserPostsItemBinding binding) {

            super(binding.getRoot());
            userPostItemBinding = binding;

        }

        void bind(Post postItem) {
           userPostItemBinding.userIdText.setText(postItem.getUserId());
            userPostItemBinding.postTitleText.setText(postItem.getTitle());
            userPostItemBinding.postBodyText.setText(postItem.getBody());
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
     List<PostModel> post = postItem.getPosts();
        holder.bind(post);
    }



    @Override
    public int getItemCount() {
        return userPosts.size();
    }
}
