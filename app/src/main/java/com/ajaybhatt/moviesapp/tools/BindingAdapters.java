package com.ajaybhatt.moviesapp.tools;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.net.URLEncoder;

public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(view);
    }

    @BindingAdapter("bind:searchText")
    public static void navigateToTrailers(final View view, final String text) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("android-app://com.google.android.youtube/http/www.youtube.com/results?search_query=" + URLEncoder.encode(text)));
                    v.getContext().startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/results?search_query=" + URLEncoder.encode(text)));
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

}
