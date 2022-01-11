package com.example.rxjavaapp.di;

import com.example.rxjavaapp.data.remote.PostsApi;
import com.example.rxjavaapp.data.remote.PostsService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    public PostsApi provideCountryApi(){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PostsApi.class);

    }

    @Provides
    public PostsService provideCountriesService(){
        return  PostsService.getInstance();
    }
}
