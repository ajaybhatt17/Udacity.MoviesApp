package com.ajaybhatt.moviesapp.ui.view;

import com.ajaybhatt.moviesapp.models.DiscoverModel;

public interface HomeView {

    void showMovies(DiscoverModel discoverModel);

    void showError(String message);

}
