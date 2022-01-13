package com.example.rxjavaapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PostEntity.class}, version = 4, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostsDAO getPostsDAO();

    public static AppDatabase appDB;

    public static AppDatabase getInstance(Context context) {
        if (null == appDB) {
            appDB = buildDatabaseInstance(context);
        }
        return appDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "Posts.DB")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        appDB = null;
    }
}
