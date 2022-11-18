package com.example.randomuserstest;

import com.example.randomuserstest.model.Result;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface RandomUserAPI {

    // Request REST from type Get with complement URL
    @GET("api/?results=5&inc=name&noinfo")
    Call <Result> getResults();

}
