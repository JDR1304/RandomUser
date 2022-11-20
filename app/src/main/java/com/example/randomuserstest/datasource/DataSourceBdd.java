package com.example.randomuserstest.datasource;

import androidx.lifecycle.LiveData;

import com.example.randomuserstest.database.UserDao;
import com.example.randomuserstest.model.User;

import java.util.List;

public class DataSourceBdd {

    private UserDao userDao;

    public DataSourceBdd(){
    }

    public DataSourceBdd(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<User>> getUsersFromBdd() {
        return userDao.getUsers();
    }

    public void insertUsers(List<User> users){
         userDao.insertUsers(users);
    }

    public void deleteAllUsers(){
        userDao.deleteAll();
    }
}
