package com.example.rxjavaapp;

import android.app.Application;

import com.example.rxjavaapp.di.ApiModule;
import com.example.rxjavaapp.di.DaggerApiComponent;


public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

    }
}
