package com.ajaybhatt.moviesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.DiscoverModel;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.presenter.HomePresenter;
import com.ajaybhatt.moviesapp.tools.ViewUtils;
import com.ajaybhatt.moviesapp.ui.adapter.MovieAdapter;
import com.ajaybhatt.moviesapp.ui.view.HomeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class HomePage extends AppCompatActivity implements HomeView, AdapterView.OnItemClickListener {

    private HomePresenter homePresenter;

    private static final String ORDER_DESC = "desc";
    private static final String ORDER_ASC = "asc";
    private static final String FILTER_POPULARITY = "popularity";
    private static final String FILTER_RELEASE_DATE = "release_date";
    private static final String FILTER_HIGHEST_RATED = "vote_average";

    @Bind(R.id.moviesList)
    protected GridView movieGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ButterKnife.bind(this);

        homePresenter = new HomePresenter(getApplicationContext(), this);
        homePresenter.start();
        //By Default, Load most popular movies
        homePresenter.getDiscover(FILTER_POPULARITY, ORDER_DESC);
    }


    @Override
    public void showMovies(DiscoverModel discoverModel) {
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), discoverModel.getMovies());
        movieGridView.setAdapter(movieAdapter);
        movieGridView.setOnItemClickListener(this);
    }

    @Override
    public void showError(String message) {
        ViewUtils.showToast(getApplicationContext(), message);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieModel movieModel = (MovieModel) parent.getAdapter().getItem(position);
        EventBus.getDefault().postSticky(movieModel);
        Intent intent = new Intent(this, MovieDetail.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_popularity:
                homePresenter.getDiscover(FILTER_POPULARITY, ORDER_DESC);
                break;
            case R.id.item_highest_rated:
                homePresenter.getDiscover(FILTER_HIGHEST_RATED, ORDER_DESC);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
