package com.ajaybhatt.moviesapp;

import android.app.Application;

import com.ajaybhatt.moviesapp.models.MovieModel;

public class MyApplication extends Application {

    private MovieModel movieModel;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }
}
