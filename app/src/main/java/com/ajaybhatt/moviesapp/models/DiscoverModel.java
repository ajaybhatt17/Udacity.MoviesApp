package com.ajaybhatt.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverModel {

    private int page;

    @SerializedName("results")
    private List<MovieModel> movies;

    @SerializedName("total_results")
    private int count;

    @SerializedName("total_pages")
    private int pageCount;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
