package com.ajaybhatt.moviesapp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.ajaybhatt.moviesapp.MainThreadBus;
import com.ajaybhatt.moviesapp.models.ErrorModel;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.rest.MovieRestCall;
import com.ajaybhatt.moviesapp.tools.Constants;
import com.ajaybhatt.moviesapp.ui.view.MovieDetailView;
import com.squareup.otto.Subscribe;

public class MovieDetailPresenter extends Presenter<MovieDetailView> {

    private Context context;
    private MovieRestCall movieRestCall;

    public MovieDetailPresenter(Context context, MovieDetailView movieDetailView) {
        super(new MainThreadBus(), movieDetailView);
        this.context = context;
        this.movieRestCall = new MovieRestCall(getBus());
    }

    public void getMovieDetail(String movieId) {
        movieRestCall.getDetail(movieId, Constants.getMoviesApiKey(context), "reviews,videos");
    }

    @Subscribe
    public void movieModelEvent(MovieModel movieModel) {
        getView().showMovieDetail(movieModel);
    }

    @Subscribe
    public void errorModelEvent(ErrorModel errorModel) {
        Toast.makeText(context, errorModel.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

}
