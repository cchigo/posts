package com.example.rxjavaapp.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.R;
import com.example.rxjavaapp.data.local.PostItem;
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
        void bind(PostItem postItem) {
//            PostModel postModel = postModels.get(postModels.size() - 1);
//            int totalPosts = postModels.size();
            int totalPost = postItem.getPosts().size();
            PostModel postModel = postItem.getPosts().get(totalPost -1);
            postItemBinding.userIdText.setText(postModel.getUserId());
            postItemBinding.postTitleText.setText(postModel.getTitle());
            postItemBinding.postBodyText.setText(postModel.getBody());
            postItemBinding.postCountText.setText(String.valueOf(totalPost));
            postItemBinding.favBtn.setOnClickListener(v -> {
                postItem.setSelected(true);
              postItemBinding.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
            });
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
     final PostItem postItem = new PostItem(list, false);
        holder.bind(postItem);
    }
    @Override
    public int getItemCount() {
       return posts.size();
    }

}
