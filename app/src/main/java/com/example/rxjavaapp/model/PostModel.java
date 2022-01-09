package com.example.rxjavaapp.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {
    @SerializedName("userId")
    String userId;
    @SerializedName("id")
     String id;
    @SerializedName("title")
     String title;
    @SerializedName("body")
     String body;

    public PostModel(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
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
}
