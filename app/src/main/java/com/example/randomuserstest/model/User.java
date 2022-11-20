package com.example.randomuserstest.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("name")
    @Expose
    @Embedded
    private Name name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("picture")
    @Expose
    @Embedded
    private Picture picture;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
