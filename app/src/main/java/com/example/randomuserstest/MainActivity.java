package com.example.randomuserstest;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.randomuserstest.model.Result;
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
//https://randomuser.me/api/?results=5&inc=name,picture&noinfo
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map <String, String> params = new HashMap<>();
        params.put("results", "5");
        params.put("inc", "name,email");
        params.put("noinfo", "");

        RandomUserAPI randomUserAPI = retrofit.create(RandomUserAPI.class);

        Call <Users> call = randomUserAPI.getResults(params);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                Log.e("MainActivity *********", response.body().toString());
                if (!response.isSuccessful()){
                    textView.setText("Code: " + response.code());
                    return;
                }
                Users user = response.body();
                List <Result> list = user.getResults();
                String content = "";
                for (int i = 0; i<list.size(); i++) {
                    Log.e(TAG, "test : " + list);
                    content += "Title " + list.get(i).getName().getTitle() + "\n";
                    content += "First name " + list.get(i).getName().getFirst() + "\n";
                    content += "Last name " + list.get(i).getName().getLast() + "\n";
                    content += "email " + list.get(i).getEmail() + "\n\n";

                }
                textView.append(content);
                }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                textView.setText(t.getMessage());
            }

        });
    }
}