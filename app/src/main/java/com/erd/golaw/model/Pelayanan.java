package com.erd.golaw.model;

/**
 * Created by ILM on 6/13/2016.
 */
public class Pelayanan {
    private int id;
    private String name;
    private String details;
    private int completed;

    public Pelayanan(int id, String name, String details, int completed) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.completed = completed;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

}
