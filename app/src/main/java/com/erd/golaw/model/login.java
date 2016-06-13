package com.erd.golaw.model;

/**
 * Created by ILM on 6/10/2016.
 */
public class Login {
    private boolean error;
    private int id;
    private String name;
    private String description;
    private String username;
    private String apikey;

    public Login(boolean error, int id, String name, String description, String username, String apikey) {
        this.error = error;
        this.id = id;
        this.name = name;
        this.description = description;
        this.username = username;
        this.apikey = apikey;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

}
