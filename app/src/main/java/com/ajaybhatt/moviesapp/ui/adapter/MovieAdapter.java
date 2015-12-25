package com.ajaybhatt.moviesapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajaybhatt.moviesapp.R;
import com.ajaybhatt.moviesapp.models.MovieModel;
import com.ajaybhatt.moviesapp.tools.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieAdapter extends ArrayAdapter<MovieModel> {

    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<MovieModel> movieModelList) {
        super(context, 0, movieModelList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MovieModel movieModel = getItem(position);

        MovieItemHolder movieItemHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            movieItemHolder = new MovieItemHolder(convertView);
            convertView.setTag(movieItemHolder);
        } else {
            movieItemHolder = (MovieItemHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(Constants.IMAGE_BASE_URL + movieModel.getSmallPosterPath())
            .diskCacheStrategy(DiskCacheStrategy.ALL). fitCenter().into(movieItemHolder.movieBackImage);
        movieItemHolder.movieTitle.setText(movieModel.getTitle());

        return convertView;
    }

    class MovieItemHolder {

        @Bind(R.id.movie_back)
        public ImageView movieBackImage;

        @Bind(R.id.movie_title)
        public TextView movieTitle;

        public MovieItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
