package com.example.randomuserstest.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.randomuserstest.Utils;
import com.example.randomuserstest.datasource.DataSourceApi;
import com.example.randomuserstest.datasource.DataSourceBdd;
import com.example.randomuserstest.model.User;

import java.util.List;

public class UserDataRepository {

    private final DataSourceBdd dataSourceBdd;
    private final DataSourceApi dataSourceApi;
    private final Context context;

    public UserDataRepository(DataSourceApi dataSourceApi, DataSourceBdd dataSourceBdd,
                              Context context) {
        this.dataSourceApi = dataSourceApi;
        this.dataSourceBdd = dataSourceBdd;
        this.context = context;
    }

    public LiveData<List<User>> getUsers() {
        if (Utils.isInternetAvailable(context)) {
            //get users from api if internet available
            return dataSourceApi.getUsersFromAPi();
        } else {
            // get users from bdd if internet
            return dataSourceBdd.getUsersFromBdd();
        }
    }

    public void setUsersInBdd(List<User> users) {
        dataSourceBdd.insertUsers(users);
    }
}
