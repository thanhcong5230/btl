package com.example.coffee;



import android.content.Context;
import android.net.Uri;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class}, version = 1)

public abstract class Userdatabase extends RoomDatabase {
    private static final String DATABASE_NAME="user.db";
    private static Userdatabase instance;
    public static  synchronized Userdatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), Userdatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();
}

