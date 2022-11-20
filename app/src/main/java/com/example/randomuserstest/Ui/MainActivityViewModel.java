package com.example.randomuserstest.Ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randomuserstest.model.User;
import com.example.randomuserstest.repository.UserDataRepository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends ViewModel {

    private UserDataRepository userDataRepository;
    private Executor executor;

    public MainActivityViewModel(UserDataRepository userDataRepository, Executor executor) {
        this.userDataRepository = userDataRepository;
        this.executor = executor;
    }

    public LiveData<List<User>> getUsers() {
        return userDataRepository.getUsers();
    }

    public void setUsersInBdd(List<User> users){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDataRepository.setUsersInBdd(users);
            }
        });

    }

}
