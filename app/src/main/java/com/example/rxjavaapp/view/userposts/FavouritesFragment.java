package com.example.rxjavaapp.view.userposts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjavaapp.FirstFragment;
import com.example.rxjavaapp.R;
import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.databinding.FragmentFavouritesBinding;
import com.example.rxjavaapp.databinding.FragmentFirstBinding;
import com.example.rxjavaapp.view.OnItemClickListener;
import com.example.rxjavaapp.view.PostsAdapter;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavouritesFragment extends Fragment {

    private FragmentFavouritesBinding binding;
    private ListViewModel listViewModel;
    private PostsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        listViewModel.getFavouritePost(true);
        adapter = new PostsAdapter(new HashMap<>(), new OnItemClickListener() {
            @Override
            public void onItemClick(String userId) {}

            @Override
            public void onFavouriteClick(List<Post> post) {}
        });
        listViewModel.favouritePosts.observe(getViewLifecycleOwner(), favouritePosts -> {
            adapter.updateCountries(listViewModel.groupedList(favouritePosts));
        });
        binding.favouritePostsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favouritePostsRv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}