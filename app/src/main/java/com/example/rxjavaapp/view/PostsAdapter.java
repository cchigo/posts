package com.example.rxjavaapp.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.databinding.PostItemBinding;
import com.example.rxjavaapp.model.PostModel;

import java.util.ArrayList;
import java.util.Map;
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private Map<String, ArrayList<PostModel>> posts;
    public PostsAdapter(Map<String, ArrayList<PostModel>> posts) {
        this.posts = posts;
    }
    public void updateCountries(Map<String, ArrayList<PostModel>> postsList) {
        posts = postsList;
    }
    class PostsViewHolder extends RecyclerView.ViewHolder {
        private PostItemBinding postItemBinding;
        public PostsViewHolder(PostItemBinding binding) {
            super(binding.getRoot());
            postItemBinding = binding;
        }
        void bind(ArrayList<PostModel> postModels) {
            PostModel postModel = postModels.get(postModels.size() - 1);
            int totalPosts = postModels.size();
            postItemBinding.userIdText.setText(postModel.getUserId());
            postItemBinding.postTitleText.setText(postModel.getTitle());
            postItemBinding.postBodyText.setText(postModel.getBody());
            postItemBinding.postCountText.setText(String.valueOf(totalPosts));
        }
    }
    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        ArrayList<PostModel> list = (ArrayList<PostModel>) posts.values().toArray()[position];
        holder.bind(list);
    }
    @Override
    public int getItemCount() {
        Log.d("COUNT_TAG", "getItemCount:"+ posts.size());
        return posts.size();
    }

}
