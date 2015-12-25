package com.ajaybhatt.moviesapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MovieDetail extends AppCompatActivity {

    @Bind(R.id.movie_title)
    protected TextView movieTitle;

    @Bind(R.id.movie_back)
    protected ImageView movieImage;

    @Bind(R.id.movie_rating)
    protected TextView movieRating;

    @Bind(R.id.movie_releasing_date)
    protected TextView movieReleaseDate;

    @Bind(R.id.movie_overview)
    protected TextView movieOverview;

    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_detail);
        ButterKnife.bind(this);

        movieModel = EventBus.getDefault().removeStickyEvent(MovieModel.class);

        if(movieModel==null) {
            onBackPressed();
            return;
        }

        setTopbar();

        fillDetails();

    }

    /**
     * Populate movie data in view
     */
    private void fillDetails() {
        Glide.with(getApplicationContext()).load(movieModel.getLargeBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(movieImage);

        movieTitle.setText(movieModel.getTitle());
        movieRating.setText(String.format(getString(R.string.movie_detail_rating), String.valueOf(movieModel.getVoteAverage())));
        movieReleaseDate.setText(String.format(getString(R.string.movie_detail_release_date), movieModel.getReleaseDate()));
        movieOverview.setText(movieModel.getOverview());
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
}
