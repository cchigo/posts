package com.example.rxjavaapp.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<PostEntity> posts);

    @Update
    public void updateFavourite(List<PostEntity> post);

    @Query("select * from posts")
    Flowable<List<PostEntity>> getAllPosts();

    @Query("select * from posts where userId ==:userId")
    Flowable<List<PostEntity>> getPostsByUserId(String userId);

    @Query("select * from posts where is_favourite =:isFavourite")
    Flowable<List<PostEntity>> getFavourites(Boolean isFavourite);

}
