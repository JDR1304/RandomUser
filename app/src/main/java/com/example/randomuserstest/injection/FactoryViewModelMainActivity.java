package com.example.randomuserstest.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.randomuserstest.Ui.MainActivityViewModel;
import com.example.randomuserstest.model.User;
import com.example.randomuserstest.repository.UserDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class FactoryViewModelMainActivity implements ViewModelProvider.Factory  {

    private final UserDataRepository userDataRepository;
    private final Executor executor;

    public FactoryViewModelMainActivity(UserDataRepository userDataRepository, Executor executor) {
        this.userDataRepository = userDataRepository;
        this.executor = executor;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(userDataRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
