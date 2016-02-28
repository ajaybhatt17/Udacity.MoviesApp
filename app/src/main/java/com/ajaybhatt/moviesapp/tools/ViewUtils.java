package com.ajaybhatt.moviesapp.tools;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ajaybhatt.moviesapp.models.MovieModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewUtils {

    public static void showToast(Context context, String message) {
        if(message!=null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static List<String> convertObjectToStringList(List<MovieModel> list) {
        List<String> strings = new ArrayList<>();
        Gson gson = new Gson();
        for (MovieModel movieModel : list){
            strings.add(gson.toJson(movieModel));
        }
        return strings;
    }

    public static Intent getShreIntent(MovieModel movieModel) {
        if (movieModel.getVideos().getResults().size()>0) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("Please watch %1$s's trailer %2$s", movieModel.getTitle(), movieModel.getVideos().getResults().get(0).getLink()));
            sendIntent.setType("text/plain");
            return sendIntent;
        }
        return null;
    }
}
