package com.erd.golaw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ILM on 6/9/2016.
 */
public class PelayananResponse {
    @SerializedName("pelayanan")
    private List<Pelayanan> results;

    public List<Pelayanan> getResults() {
        return results;
    }

    public void setResults(List<Pelayanan> results) {
        this.results = results;
    }

}
