package com.example.randomuserstest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("results")
    @Expose
    private List<User> results = null;

    public List <User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}


