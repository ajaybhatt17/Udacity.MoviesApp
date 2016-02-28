package com.ajaybhatt.moviesapp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.DiscoverModel;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.presenter.HomePresenter;
import com.ajaybhatt.moviesapp.tools.DatabaseSource;
import com.ajaybhatt.moviesapp.tools.ViewUtils;
import com.ajaybhatt.moviesapp.ui.fragment.MasterFragment;
import com.ajaybhatt.moviesapp.ui.fragment.SlaveFragment;
import com.ajaybhatt.moviesapp.ui.view.HomeView;
import com.ajaybhatt.moviesapp.ui.view.MasterSlaveListener;

import java.util.List;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class HomePage extends AppCompatActivity implements HomeView, MasterSlaveListener {

    private SlaveFragment mSlaveFragment;
    private HomePresenter homePresenter;
    private MasterFragment masterFragment;

    private static final String ORDER_DESC = "desc";
    private static final String ORDER_ASC = "asc";
    private static final String FILTER_POPULARITY = "popularity";
    private static final String FILTER_RELEASE_DATE = "release_date";
    private static final String FILTER_HIGHEST_RATED = "vote_average";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ButterKnife.bind(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mSlaveFragment = (SlaveFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_slave);
        }

        if (mSlaveFragment == null) {
            masterFragment = MasterFragment.getInstance(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, masterFragment)
                    .commit();
        } else {
            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_master);
            masterFragment.setMasterSlaveListener(this);
        }

        homePresenter = new HomePresenter(this, this);
        homePresenter.start();
        //By Default, Load most popular movies
        homePresenter.getDiscover(FILTER_POPULARITY, ORDER_DESC);

    }


    @Override
    public void showMovies(DiscoverModel discoverModel) {
        if (masterFragment != null) {
            masterFragment.showMovies(discoverModel);
        }
    }

    @Override
    public void showError(String message) {
        ViewUtils.showToast(getApplicationContext(), message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mSlaveFragment != null && mSlaveFragment.getMovieModel() != null) {
            menu.getItem(3).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
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
            case R.id.item_favourite:
                DiscoverModel discoverModel = new DiscoverModel();
                List<MovieModel> movieModels = DatabaseSource.getObjects(this, "favourite_movies", MovieModel.class);
                discoverModel.setMovies(movieModels);
                showMovies(discoverModel);
                break;
            case R.id.item_share:
                if (mSlaveFragment != null) {
                    Intent intent = ViewUtils.getShreIntent(mSlaveFragment.getMovieModel());
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(int position, MovieModel movieModel) {
        if (mSlaveFragment != null) {
            mSlaveFragment.setMovieModel(movieModel);
        } else {
            EventBus.getDefault().postSticky(movieModel);
            Intent intent = new Intent(this, MovieDetail.class);
            startActivity(intent);
        }
    }

}
