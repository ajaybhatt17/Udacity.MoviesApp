package com.ajaybhatt.moviesapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ajaybhatt.moviesapp.BR;
import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.MovieModel;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieModel> {

    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<MovieModel> movieModelList) {
        super(context, 0, movieModelList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.item_movie, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.setVariable(BR.movie, getItem(position));
        binding.executePendingBindings();

        return binding.getRoot();
    }
}
