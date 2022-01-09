package com.example.rxjavaapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rxjavaapp.R;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countryList)
    RecyclerView countriesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ListViewModel listViewModel;
    private PostsAdapter adapter = new PostsAdapter(new HashMap<>());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            listViewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });
        
        observerViewModel();
    }

    private void observerViewModel() {
        listViewModel.countries.observe(this, countryModels -> {
            if(countryModels != null){
                countriesList.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });
        listViewModel.countryLoadError.observe(this, isError -> {
           if(isError != null){
               listError.setVisibility(isError ? View.VISIBLE : View.GONE);
           }
        });
        listViewModel.loading.observe(this, isLoading -> {
            if(isLoading != null){
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                    listError.setVisibility(View.GONE);
                    countriesList.setVisibility(View.GONE);
                }
            }
        });
    }
}