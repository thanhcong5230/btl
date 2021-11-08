package com.example.coffee;



import android.widget.ImageButton;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;


@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int resouceImage;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int resouceImage, String name) {
        this.resouceImage = resouceImage;
        this.name = name;
    }

    public int getResouceImage() {
        return resouceImage;
    }

    public void setResouceImage(int resouceImage) {
        this.resouceImage = resouceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

