package com.example.rxjavaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rxjavaapp.data.local.Post;
import com.example.rxjavaapp.databinding.FragmentFirstBinding;
import com.example.rxjavaapp.view.OnItemClickListener;
import com.example.rxjavaapp.view.PostsAdapter;
import com.example.rxjavaapp.view.userposts.FavouritesFragment;
import com.example.rxjavaapp.viewmodel.ListViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ListViewModel listViewModel;
    private CompositeDisposable disposable = new CompositeDisposable();
    private PostsAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        adapter = new PostsAdapter(new HashMap<>(), new OnItemClickListener() {
            @Override
            public void onItemClick(String userId) {
                listViewModel.getPostsByUserId(userId);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }

            @Override
            public void onFavouriteClick(Post post) {
                listViewModel.setFavouritePost(post);
            }
        });
        binding.favbtn.setOnClickListener(v -> {
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_favouritesFragment);
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.postRecyclerView.setAdapter(adapter);

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            listViewModel.refresh();
            binding.swipeRefreshLayout.setRefreshing(false);
        });

        observerViewModel2();
    }

    private void observerViewModel2() {
        listViewModel.posts.observe(getViewLifecycleOwner(), countryModels -> {
            if (countryModels != null) {
                binding.postRecyclerView.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });
        listViewModel.loadingError.observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                binding.listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        listViewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    binding.listError.setVisibility(View.GONE);
                    binding.postRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}