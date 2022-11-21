package com.example.randomuserstest.injection;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.randomuserstest.database.RandomUsersDataBase;
import com.example.randomuserstest.datasource.DataSourceApi;
import com.example.randomuserstest.datasource.DataSourceBdd;
import com.example.randomuserstest.model.User;
import com.example.randomuserstest.repository.UserDataRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static DataSourceBdd provideDataSourceBdd(Context context) {
        RandomUsersDataBase database = RandomUsersDataBase.getInstance(context);
        return new DataSourceBdd(database.userDao());
    }

    public static MutableLiveData<List<User>> provideMutableLiveData() {
        return new MutableLiveData<>();
    }

    public static UserDataRepository provideUserDataSource(Context context) {
        DataSourceApi dataSourceApi = new DataSourceApi(provideMutableLiveData());
        DataSourceBdd dataSourceBdd = provideDataSourceBdd(context);
        return new UserDataRepository(dataSourceApi, dataSourceBdd, context);
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static FactoryViewModelMainActivity provideViewModelFactory(Context context) {
        UserDataRepository userDataRepository = provideUserDataSource(context);
        Executor executor = provideExecutor();
        return new FactoryViewModelMainActivity(userDataRepository, executor);
    }
}
