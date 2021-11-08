package com.example.coffee;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface UserDao {
    @Insert
    void insert (User user);
    @Query("SELECT * FROM user")
    List<User> getListUser();

    //truy vấn xem bàn mới nhập có trùng không
    @Query("SELECT *FROM user where name= :name")
    List<User>checktable(String name);

    // truy vấn để xóa bàn
    @Delete
    void delete(User user);

    //truy vấn update
    @Update
    void update(User user);

}
