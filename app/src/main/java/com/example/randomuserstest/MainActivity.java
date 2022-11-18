package com.example.randomuserstest;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.randomuserstest.model.Result;

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
        params.put("inc", "name");
        params.put("noinfo", "");

        RandomUserAPI randomUserAPI = retrofit.create(RandomUserAPI.class);

        Call<Result> call = randomUserAPI.getResults();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Log.e("MainActivity *********", response.body().toString());
                if (!response.isSuccessful()){
                    textView.setText("Code: " + response.code());
                    return;
                }
                Result list = response.body();

                Log.e(TAG,"test : "+list.getName());
                    /*String content = "";
                    content += "Title" + list.getName().getTitle()+ "\n";
                    content += "First name "+ list.getName().getFirst()+ "\n";
                    content += "Last name "+ list.getName().getLast()+"\n\n";
                    textView.append(content);*/
                }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                textView.setText(t.getMessage());
            }

        });
        Log.e(TAG,"test");
    }
}