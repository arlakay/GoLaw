package com.erd.golaw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ILM on 6/10/2016.
 */
public class User {
    @Expose
    @SerializedName("username")
    String username;
    @Expose
    @SerializedName("password")
    String password;

    public User(String usr, String pas ) {
        this.username = usr;
        this.password = pas;
    }

}
