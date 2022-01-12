package com.example.rxjavaapp.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rxjavaapp.model.PostModel;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Post> posts);


    @Delete
    public void deletePost(Post post);

    @Delete
    public void addFavouritePost(Post post);

    @Update
    public void changeFavourite(Post post);

//    @Query("select * from contacts")
//    public List<Contact> getContacts();
//
    @Query("select * from posts")
    Flowable <List<Post>> getAllPosts();
//
//    @Query("select * from contacts where contact_id ==:contactId")
//    public Contact getContact(long contactId);
}
