package com.mc2022.template;

import static android.icu.text.MessagePattern.ArgType.SELECT;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

@Dao
public interface userDao {
//    @Query("Select * from user" )
//    List<user> getuserlist();
    @Insert(onConflict = REPLACE)
    void  insert(user u);
    @Delete
    void delete(user u);
    @Update
    void update(user u);
    @Query("SELECT * FROM user")
    List<user> getAll();

    @Query("SELECT * FROM user WHERE email=:emailuser" )
    List<user> getUserDetail(String emailuser);

    @Query("SELECT * FROM user WHERE phoneno=:phone" )
    List<user> getUserPhone(String phone);

}
