package com.example.rxjavaapp.view.userposts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rxjavaapp.data.local.PostItem;
import com.example.rxjavaapp.databinding.FragmentSecondBinding;
import com.example.rxjavaapp.view.PostsAdapter;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import java.util.List;

public class UserPostFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ListViewModel listViewModel;
    private UserPostAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        listViewModel.userPosts.observe(getViewLifecycleOwner(), postItem -> {
            Log.d("SECOND_TAG_1", postItem.toString());
           // List<PostItem> list = postItem;
            adapter = new UserPostAdapter(postItem);
//            if (postItem != null) {
//                Log.d("SECOND_TAG", "onViewCreated: " );
//
//            }
        });


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(UserPostFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}