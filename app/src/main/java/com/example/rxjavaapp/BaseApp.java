//package com.example.rxjavaapp;
//
//import android.app.Application;
//import com.example.rxjavaapp.di.DaggerApiComponent;
//import com.example.rxjavaapp.di.ApiComponent;
//import com.example.rxjavaapp.di.ApiModule;
//
//
//
//public class BaseApp extends Application {
//
//    private static BaseApp baseApp;
//    private ApiComponent apiComponent;
//
//    public static BaseApp getBaseApp() {
//        return baseApp;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        baseApp = this;
//        apiComponent = DaggerApiComponent.builder()
//                .apiModule(new ApiModule())
//                .build();
//    }
//
//    public ApiComponent getApiComponent() {
//        return apiComponent;
//    }
//}
