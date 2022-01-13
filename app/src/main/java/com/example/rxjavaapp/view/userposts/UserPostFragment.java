package com.example.rxjavaapp.view.userposts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rxjavaapp.data.local.PostEntity;
import com.example.rxjavaapp.databinding.FragmentSecondBinding;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import java.util.ArrayList;

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
        //ArrayList<PostEntity> postItem = listViewModel.userPosts.getValue();
      //  adapter = new UserPostAdapter(postItem);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.userPostsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.userPostsRV.setAdapter(adapter);
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