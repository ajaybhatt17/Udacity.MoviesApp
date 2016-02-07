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
import com.ajaybhatt.moviesapp.models.ReviewResult;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<ReviewResult> {

    private LayoutInflater inflater;

    public ReviewAdapter(Context context, List<ReviewResult> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.item_review, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.setVariable(BR.review, getItem(position));
        binding.executePendingBindings();

        return binding.getRoot();
    }

}
