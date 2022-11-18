package com.example.randomuserstest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    @Expose
    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
