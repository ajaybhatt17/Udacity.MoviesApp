package com.ajaybhatt.moviesapp.tools;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(view);
    }
}
