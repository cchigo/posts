package com.example.rxjavaapp.view.userposts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.databinding.UserPostsItemBinding;

import java.util.ArrayList;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.UserPostsViewHolder> {
    private ArrayList<PostEntity> postItem;

    public UserPostAdapter(ArrayList<PostEntity> postItem) {
        this.postItem = postItem;
    }

    class UserPostsViewHolder extends RecyclerView.ViewHolder {

        private UserPostsItemBinding userPostItemBinding;

        public UserPostsViewHolder(UserPostsItemBinding binding) {

            super(binding.getRoot());
            userPostItemBinding = binding;

        }

        void bind(PostEntity postModel) {
//           userPostItemBinding.userIdText.setText(postModel.getUserId());
//            userPostItemBinding.postTitleText.setText(postModel.getTitle());
//            userPostItemBinding.postBodyText.setText(postModel.getBody());
//            userPostItemBinding.postItemLayout.setOnClickListener(v -> {
//             //   listener.onItemClick(postItem);
//            });
        }
    }

    @NonNull
    @Override
    public UserPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserPostAdapter.UserPostsViewHolder(UserPostsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserPostsViewHolder holder, int position) {
     PostEntity post = postItem.get(position);
        holder.bind(post);
    }



    @Override
    public int getItemCount() {
        return postItem.size();
    }
}
