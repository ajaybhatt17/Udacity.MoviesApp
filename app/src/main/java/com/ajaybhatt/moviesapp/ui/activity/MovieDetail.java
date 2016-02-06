package com.ajaybhatt.moviesapp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ajaybhatt.moviesapp.MyApplication;
import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.databinding.LayoutMovieDetailBinding;
import com.ajaybhatt.moviesapp.models.MovieModel;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MovieDetail extends AppCompatActivity {

    private MovieModel movieModel;

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
