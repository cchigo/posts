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
    private CompositeDisposable disposable = new CompositeDisposable();
    private PostsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        listViewModel.getFavouritePost(true);
        Log.d("FAVOURITE_TAG_", "onViewCreated: "+ listViewModel._favouritePosts.getValue());


        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewModel._favouritePosts.observe(getViewLifecycleOwner(), countryModels -> {
            if (countryModels != null) {


                Log.d("FAVOURITE_TAG", "onViewCreated: "+ countryModels.toString());

//                binding.favouritePostsRv.setVisibility(View.VISIBLE);
//                adapter = new PostsAdapter(new HashMap<>(), new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(String userId) {
//                        listViewModel.getPostsByUserId(userId);
//                        NavHostFragment.findNavController(FavouritesFragment.this)
//                                .navigate(R.id.action_favouritesFragment_to_SecondFragment);
//                    }
//
//                    @Override
//                    public void onFavouriteClick(Post post) {
//                        listViewModel.setFavouritePost(post);
//                    }
//                });
            }
        });




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}