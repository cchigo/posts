package com.example.rxjavaapp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rxjavaapp.databinding.ActivityMainBinding;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private ListViewModel listViewModel;
    private PostsAdapter adapter = new PostsAdapter(new HashMap<>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.refresh();

        mainBinding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.postRecyclerView.setAdapter(adapter);

        mainBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            listViewModel.refresh();
            mainBinding.swipeRefreshLayout.setRefreshing(false);
        });
        
        observerViewModel();
    }

    private void observerViewModel() {
        listViewModel.countries.observe(this, countryModels -> {
            if(countryModels != null){
                mainBinding.postRecyclerView.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });
        listViewModel.countryLoadError.observe(this, isError -> {
           if(isError != null){
               mainBinding.listError.setVisibility(isError ? View.VISIBLE : View.GONE);
           }
        });
        listViewModel.loading.observe(this, isLoading -> {
            if(isLoading != null){
                mainBinding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                    mainBinding.listError.setVisibility(View.GONE);
                    mainBinding.postRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}