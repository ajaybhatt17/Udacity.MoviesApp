package com.ajaybhatt.moviesapp.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ajaybhatt.moviesapp.MyApplication;
import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.databinding.LayoutMovieDetailBinding;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.models.TrailerModel;
import com.ajaybhatt.moviesapp.presenter.MovieDetailPresenter;
import com.ajaybhatt.moviesapp.tools.DatabaseSource;
import com.ajaybhatt.moviesapp.tools.ViewUtils;
import com.ajaybhatt.moviesapp.ui.adapter.ReviewAdapter;
import com.ajaybhatt.moviesapp.ui.adapter.TrailerAdapter;
import com.ajaybhatt.moviesapp.ui.view.MovieDetailView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MovieDetail extends AppCompatActivity implements MovieDetailView, AdapterView.OnItemClickListener, View.OnClickListener {

    private MovieModel movieModel;

    @Bind(R.id.reviews)
    protected ListView reviews;

    @Bind(R.id.trailers)
    protected ListView trailers;

    private MovieDetailPresenter movieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.layout_movie_detail);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            movieModel = EventBus.getDefault().removeStickyEvent(MovieModel.class);
            ((MyApplication) getApplication()).setMovieModel(movieModel);
        } else {
            movieModel = ((MyApplication) getApplication()).getMovieModel();
        }

        if (movieModel == null) {
            onBackPressed();
            return;
        }

        setTopbar();
        binding.setMovie(movieModel);
        if (movieDetailPresenter == null) {
            movieDetailPresenter = new MovieDetailPresenter(this, this);
        }
        movieDetailPresenter.start();
        movieDetailPresenter.getMovieDetail(String.valueOf(movieModel.getId()));
        boolean isFavourite = DatabaseSource.getBoolean(this, "isFavourite_" + String.valueOf(movieModel.getId()));
        movieModel.setIsFavourite(isFavourite);
    }

    private void setTopbar() {
        setTitle(movieModel.getTitle());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showMovieDetail(MovieModel movieModel) {
        this.movieModel.setReviews(movieModel.getReviews());
        this.movieModel.setTrailers(movieModel.getTrailers());
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, movieModel.getReviews().getResults());
        reviews.setAdapter(reviewAdapter);
        TrailerAdapter trailerAdapter = new TrailerAdapter(this, movieModel.getTrailers().getAllTrailers());
        trailers.setAdapter(trailerAdapter);
        trailers.setOnItemClickListener(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Adapter parentAdapter = parent.getAdapter();
        if (parentAdapter instanceof TrailerAdapter) {
            TrailerModel trailerModel = (TrailerModel) parentAdapter.getItem(position);
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerModel.getSource()));
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailerModel.getSource()));
                startActivity(intent);
            }
        }
    }

    @OnClick(R.id.favouriteButton)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favouriteButton:
                movieModel.setIsFavourite(true);
                DatabaseSource.putBoolean(this, "isFavourite_" + String.valueOf(movieModel.getId()), true);
                List<MovieModel> favouriteMovieList = DatabaseSource.getObjects(this, "favourite_movies", MovieModel.class);
                if (!favouriteMovieList.contains(movieModel)) {
                    favouriteMovieList.add(movieModel);
                    List<String> output = ViewUtils.convertObjectToStringList(favouriteMovieList);
                    DatabaseSource.putArray(this, "favourite_movies", output.toArray(new String[output.size()]));
                }
                break;
        }
    }
}
