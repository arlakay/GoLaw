package com.erd.golaw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ILM on 6/9/2016.
 */
public class AdvokatResponse {
    @SerializedName("students")
    private List<Advokat> results;

    public List<Advokat> getResults() {
        return results;
    }

    public void setResults(List<Advokat> results) {
        this.results = results;
    }

}
