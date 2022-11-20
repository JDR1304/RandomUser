package com.example.randomuserstest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.randomuserstest.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RandomUsersDataBase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile RandomUsersDataBase INSTANCE;

    // --- DAO ---
    public abstract UserDao userDao();

    // --- INSTANCE ---
    public static RandomUsersDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RandomUsersDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RandomUsersDataBase.class, "MyDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
