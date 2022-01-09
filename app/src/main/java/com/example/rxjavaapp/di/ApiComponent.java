package com.example.rxjavaapp.di;

//specifies which modules and location the component manages

import com.example.rxjavaapp.model.PostsService;
import com.example.rxjavaapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(PostsService service);

    void inject(ListViewModel listViewModel);
}
