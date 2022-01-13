package com.example.rxjavaapp.data.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "posts")
public class PostEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    String id;

    @ColumnInfo(name="userId")
    String userId;

    @ColumnInfo(name="title")
    String title;

    @ColumnInfo(name="body")
    String body;

    @ColumnInfo(name="is_favourite")
    Boolean isFavourite;

    public PostEntity(String userId, @NonNull String id, String title, String body, Boolean isFavourite) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.isFavourite = isFavourite;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @androidx.annotation.NonNull
    public String getId() {
        return id;
    }

    public void setId(@androidx.annotation.NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
