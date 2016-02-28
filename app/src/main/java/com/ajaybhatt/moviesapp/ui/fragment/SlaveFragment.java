package com.ajaybhatt.moviesapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.databinding.LayoutMovieDetailBinding;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.models.VideoResult;
import com.ajaybhatt.moviesapp.presenter.MovieDetailPresenter;
import com.ajaybhatt.moviesapp.tools.AppNavigator;
import com.ajaybhatt.moviesapp.tools.DatabaseSource;
import com.ajaybhatt.moviesapp.tools.ViewUtils;
import com.ajaybhatt.moviesapp.ui.adapter.ReviewAdapter;
import com.ajaybhatt.moviesapp.ui.adapter.TrailerAdapter;
import com.ajaybhatt.moviesapp.ui.view.MovieDetailView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlaveFragment extends Fragment implements MovieDetailView, AdapterView.OnItemClickListener, View.OnClickListener {

    private MovieModel movieModel;
    private MovieDetailPresenter movieDetailPresenter;

    @Bind(R.id.reviews)
    protected ListView reviews;

    @Bind(R.id.trailers)
    protected ListView trailers;

    private LayoutMovieDetailBinding binding;

    public SlaveFragment() {

    }

    public static SlaveFragment getInstance(MovieModel movieModel) {
        SlaveFragment slaveFragment = new SlaveFragment();
        slaveFragment.movieModel = movieModel;
        return slaveFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_movie_detail, container, false);
        ButterKnife.bind(this, binding.getRoot());
        binding.setMovie(null);
        if (movieDetailPresenter == null) {
            movieDetailPresenter = new MovieDetailPresenter(getContext(), this);
        }
        movieDetailPresenter.start();
        return binding.getRoot();
    }

    @Override
    public void showMovieDetail(MovieModel movieModel) {
        this.movieModel.setReviews(movieModel.getReviews());
        this.movieModel.setVideos(movieModel.getVideos());
        ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), movieModel.getReviews().getResults());
        reviews.setAdapter(reviewAdapter);
        TrailerAdapter trailerAdapter = new TrailerAdapter(getContext(), movieModel.getVideos().getResults());
        trailers.setAdapter(trailerAdapter);
        trailers.setOnItemClickListener(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        binding.setMovie(movieModel);
        movieDetailPresenter.getMovieDetail(String.valueOf(movieModel.getId()));
        boolean isFavourite = DatabaseSource.getBoolean(getContext(), "isFavourite_" + String.valueOf(movieModel.getId()));
        movieModel.setIsFavourite(isFavourite);
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Adapter parentAdapter = parent.getAdapter();
        if (parentAdapter instanceof TrailerAdapter) {
            AppNavigator.navigateToTrailer(getContext(), (VideoResult) parentAdapter.getItem(position));
        }
    }

    @OnClick(R.id.favouriteButton)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favouriteButton:
                movieModel.setIsFavourite(true);
                DatabaseSource.putBoolean(getContext(), "isFavourite_" + String.valueOf(movieModel.getId()), true);
                List<MovieModel> favouriteMovieList = DatabaseSource.getObjects(getContext(), "favourite_movies", MovieModel.class);
                if (!favouriteMovieList.contains(movieModel)) {
                    favouriteMovieList.add(movieModel);
                    List<String> output = ViewUtils.convertObjectToStringList(favouriteMovieList);
                    DatabaseSource.putArray(getContext(), "favourite_movies", output.toArray(new String[output.size()]));
                }
                break;
        }
    }

}
