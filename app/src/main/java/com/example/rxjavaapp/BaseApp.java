package com.example.rxjavaapp;


import android.app.Application;

import dagger.hilt.EntryPoint;
import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp()
public class BaseApp extends Application {

    private static BaseApp baseApp;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }


}
