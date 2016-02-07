package com.ajaybhatt.moviesapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.DiscoverModel;
import com.ajaybhatt.moviesapp.presenter.HomePresenter;
import com.ajaybhatt.moviesapp.ui.adapter.MovieAdapter;
import com.ajaybhatt.moviesapp.ui.view.MasterSlaveListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MasterFragment extends Fragment implements AdapterView.OnItemClickListener {

    private HomePresenter homePresenter;

    @Bind(R.id.moviesList)
    protected GridView movieGridView;

    private MovieAdapter movieAdapter;

    private MasterSlaveListener masterSlaveListener;

    public MasterFragment() {

    }

    public static MasterFragment getInstance(MasterSlaveListener masterSlaveListener) {
        MasterFragment masterFragment = new MasterFragment();
        masterFragment.masterSlaveListener = masterSlaveListener;
        return masterFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void setMasterSlaveListener(MasterSlaveListener masterSlaveListener) {
        this.masterSlaveListener = masterSlaveListener;
    }

    public void showMovies(DiscoverModel discoverModel) {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(getContext(), discoverModel.getMovies());
            movieGridView.setAdapter(movieAdapter);
            movieGridView.setOnItemClickListener(this);
        } else {
            movieAdapter.clear();
            movieAdapter.addAll(discoverModel.getMovies());
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (masterSlaveListener != null) {
            masterSlaveListener.onItemSelected(position, movieAdapter.getItem(position));
        }
    }
}
