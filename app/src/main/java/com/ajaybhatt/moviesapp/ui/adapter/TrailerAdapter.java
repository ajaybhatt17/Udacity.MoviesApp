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
import com.ajaybhatt.moviesapp.models.TrailerModel;
import com.ajaybhatt.moviesapp.models.VideoResult;

import java.util.List;

public class TrailerAdapter extends ArrayAdapter<VideoResult> {

    private LayoutInflater inflater;

    public TrailerAdapter(Context context, List<VideoResult> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.item_trailer, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.setVariable(BR.trailer, getItem(position));
        binding.executePendingBindings();

        return binding.getRoot();
    }
}
