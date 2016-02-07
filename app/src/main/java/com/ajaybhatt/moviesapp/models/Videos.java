package com.ajaybhatt.moviesapp.models;

import java.util.ArrayList;
import java.util.List;

public class Videos {

    private List<VideoResult> results = new ArrayList<>();

    public List<VideoResult> getResults() {
        return results;
    }

    public void setResults(List<VideoResult> results) {
        this.results = results;
    }
}
