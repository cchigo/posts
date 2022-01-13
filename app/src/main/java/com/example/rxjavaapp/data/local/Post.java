package com.example.rxjavaapp.data.local;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    String userId;
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;
    @SerializedName("is_favourite")
    Boolean isFavourite;

    public Post(String userId, String id, String title, String body, Boolean isFavourite) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
