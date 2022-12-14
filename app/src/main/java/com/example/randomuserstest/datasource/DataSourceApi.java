package com.example.randomuserstest.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.randomuserstest.RandomUserAPI;
import com.example.randomuserstest.model.User;
import com.example.randomuserstest.model.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;

public class DataSourceApi {

    private List<User> listUsers;
    private final MutableLiveData<List<User>> usersMutableLiveData;

    public DataSourceApi(MutableLiveData<List<User>> usersMutableLiveData) {
        this.usersMutableLiveData = usersMutableLiveData;
    }

    public LiveData<List<User>> getUsersFromAPi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, String> params = new HashMap<>();
        params.put("results", "12");
        params.put("inc", "name,email,picture");
        params.put("noinfo", "");

        RandomUserAPI randomUserAPI = retrofit.create(RandomUserAPI.class);

        Call<Users> call = randomUserAPI.getResults(params);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                Users user = response.body();

                if (user!=null) {
                    listUsers = user.getResults();
                }
                usersMutableLiveData.setValue(listUsers);
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {

            }
        });
        return usersMutableLiveData;
    }
}
