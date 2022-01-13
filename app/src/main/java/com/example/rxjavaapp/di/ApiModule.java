package com.example.rxjavaapp.di;

import android.content.Context;

import com.example.rxjavaapp.data.repository.DataRepository;
import com.example.rxjavaapp.data.local.AppDatabase;
import com.example.rxjavaapp.data.local.LocalDataSource;
import com.example.rxjavaapp.data.model.mapper.DTOModelMapper;
import com.example.rxjavaapp.data.model.mapper.EntityModelMapper;
import com.example.rxjavaapp.data.remote.PostsApi;
import com.example.rxjavaapp.data.remote.PostsService;
import com.example.rxjavaapp.data.remote.RemoteDataSource;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApiModule {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    public PostsApi provideCountryApi() {

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
    public PostsService providePostsService(PostsApi postsApi) {
        return new PostsService(postsApi);
    }


    @Provides
    public AppDatabase providesAppDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    public LocalDataSource providesLocalDataSource(AppDatabase database) {
        return new LocalDataSource(database);
    }


    @Provides
    public RemoteDataSource provideRemoteDataSource(PostsService postsService) {
        return new RemoteDataSource(postsService);
    }

    @Provides
    public EntityModelMapper providesEntityModelMapper(){
        return new EntityModelMapper();
    }
    @Provides
    public DTOModelMapper providesDTOModelMapper(){
        return new DTOModelMapper();
    }

    @Provides
    public DataRepository providesDataRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource, EntityModelMapper entityModelMapper, DTOModelMapper dtoModelMapper) {
        return new DataRepository(localDataSource, remoteDataSource, entityModelMapper, dtoModelMapper);
    }
}
