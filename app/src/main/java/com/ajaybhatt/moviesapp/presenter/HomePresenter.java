package com.ajaybhatt.moviesapp.presenter;

import android.content.Context;

import com.ajaybhatt.moviesapp.MainThreadBus;
import com.ajaybhatt.moviesapp.models.DiscoverModel;
import com.ajaybhatt.moviesapp.models.ErrorModel;
import com.ajaybhatt.moviesapp.rest.MovieRestCall;
import com.ajaybhatt.moviesapp.tools.Constants;
import com.ajaybhatt.moviesapp.ui.view.HomeView;
import com.squareup.otto.Subscribe;

public class HomePresenter extends Presenter<HomeView>{

    private MovieRestCall movieRestCall;
    private Context context;

    public HomePresenter(Context context, HomeView homeView) {
        super(new MainThreadBus(), homeView);
        this.context = context;
        movieRestCall = new MovieRestCall(getBus());
    }

    public void getDiscover(String filter, String order) {
        movieRestCall.discover(Constants.getMoviesApiKey(context), filter, order);
    }

    @Subscribe
    public void discoverModelEvent(DiscoverModel discoverModel) {
        getView().showMovies(discoverModel);
    }

    @Subscribe
    public void errorModelEvent(ErrorModel errorModel) {
        getView().showError(errorModel.getErrorMessage());
    }

}
