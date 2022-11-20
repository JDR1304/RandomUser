package com.example.randomuserstest.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.randomuserstest.R;

import com.example.randomuserstest.injection.FactoryViewModelMainActivity;
import com.example.randomuserstest.injection.Injection;
import com.example.randomuserstest.model.User;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        initViewModel();
        getUsers();


        //mainActivityViewModel.getUsers();

/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map <String, String> params = new HashMap<>();
        params.put("results", "12");
        params.put("inc", "name,email,picture");
        params.put("noinfo", "");

        RandomUserAPI randomUserAPI = retrofit.create(RandomUserAPI.class);

        Call <Users> call = randomUserAPI.getResults(params);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                Log.e("MainActivity *********", response.body().toString());
                if (!response.isSuccessful()){
                    return;
                }
                Users user = response.body();
                List <User> list = user.getResults();
                userRecyclerViewAdapter = new UserRecyclerViewAdapter(list);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(userRecyclerViewAdapter);

                }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }

        });*/
    }

    public void initViewModel (){
        FactoryViewModelMainActivity viewModelFactory = Injection.provideViewModelFactory(this);
        this.mainActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
    }

    public void getUsers(){
        Observer<List<User>> results = new Observer <List<User>>(){

            @Override
            public void onChanged(List<User> users) {
                userRecyclerViewAdapter = new UserRecyclerViewAdapter(users);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(userRecyclerViewAdapter);
                mainActivityViewModel.setUsersInBdd(users);
            }
        };
        mainActivityViewModel.getUsers().observe(this, results);
    }
}