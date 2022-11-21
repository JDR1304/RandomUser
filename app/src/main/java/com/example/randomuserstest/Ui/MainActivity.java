package com.example.randomuserstest.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.randomuserstest.R;

import com.example.randomuserstest.Utils;
import com.example.randomuserstest.injection.FactoryViewModelMainActivity;
import com.example.randomuserstest.injection.Injection;
import com.example.randomuserstest.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private List<User> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        initViewModel();
        initRecyclerView();
        getUsers();
    }

    public void initViewModel (){
        FactoryViewModelMainActivity viewModelFactory = Injection.provideViewModelFactory(this);
        this.mainActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
    }

    public void initRecyclerView (){
        userRecyclerViewAdapter = new UserRecyclerViewAdapter(usersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(userRecyclerViewAdapter);
    }

    public void getUsers(){

        Observer<List<User>> results = new Observer <List<User>>(){
            @Override
            public void onChanged(List<User> users) {
                usersList.clear();
                usersList.addAll(users);
                userRecyclerViewAdapter.notifyDataSetChanged();
                if (Utils.isInternetAvailable(getApplicationContext())){
                    mainActivityViewModel.setUsersInBdd(users);
                }
            }
        };
        mainActivityViewModel.getUsers().observe(this, results);
    }
}