package com.example.randomuserstest;

import com.example.randomuserstest.model.Users;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface RandomUserAPI {

    // Request REST from type Get with complement URL
    @GET("api/")
    Call <Users> getResults(@QueryMap Map<String, String> params);

}
