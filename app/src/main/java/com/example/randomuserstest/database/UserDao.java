package com.example.randomuserstest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.randomuserstest.model.User;

import java.util.List;

@Dao
public interface UserDao {

    /*
     * Allow to avoid the conflict when we create a user with the same id
     * it's going to crush the old one and replace by the new one
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List <User> users);

    @Query("SELECT * FROM User")
    LiveData <List<User>> getUsers();

    @Query("DELETE FROM User")
    void deleteAll();

}
