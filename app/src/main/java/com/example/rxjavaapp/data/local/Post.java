package com.example.rxjavaapp.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post  {
    @ColumnInfo(name="post_id")
    @PrimaryKey(autoGenerate =true)
    private long post_id;
    @ColumnInfo(name="userId")
    String userId;
    @ColumnInfo(name="id")
    String id;
    @ColumnInfo(name="title")
    String title;
    @ColumnInfo(name="body")
    String body;
    @ColumnInfo(name="is_favourite")
    Boolean isSelected;

    public Post(long post_id, String userId, String id, String title, String body, Boolean isSelected) {
        this.post_id = post_id;
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.isSelected = isSelected;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
