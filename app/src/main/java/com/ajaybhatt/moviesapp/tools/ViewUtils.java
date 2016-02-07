package com.ajaybhatt.moviesapp.tools;

import android.content.Context;
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
}
