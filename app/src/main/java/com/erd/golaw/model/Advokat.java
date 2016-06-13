package com.erd.golaw.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ILM on 6/9/2016.
 */
public class Advokat {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("description")
    private String description;


    public Advokat(String id, String name, String username, String description) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
