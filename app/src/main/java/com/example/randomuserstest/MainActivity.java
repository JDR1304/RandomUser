package com.example.randomuserstest;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.randomuserstest.model.User;
import com.example.randomuserstest.model.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserRecyclerViewAdapter userRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);


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

        });
    }
}