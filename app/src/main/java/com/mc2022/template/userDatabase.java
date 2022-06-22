package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {user.class},exportSchema = false,version=1)
public abstract class userDatabase extends RoomDatabase {
    public static final String dbname="user_db";
    public static userDatabase userinstance;
    public static synchronized userDatabase getInstance(Context context){
        if(userinstance==null){
            userinstance= Room.databaseBuilder(context.getApplicationContext(), userDatabase.class,dbname).
                    allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();
        }
        return userinstance;
    }

    public abstract userDao userDao();
//    public abstract user userDao();
}

