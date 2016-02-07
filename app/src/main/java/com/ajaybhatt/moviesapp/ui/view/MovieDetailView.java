package com.ajaybhatt.moviesapp.ui.view;

import com.ajaybhatt.moviesapp.models.MovieModel;

public interface MovieDetailView {

    void showMovieDetail(MovieModel movieModel);

    void showProgress();

    void hideProgress();

}
