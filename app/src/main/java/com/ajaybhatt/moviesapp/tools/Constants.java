package com.ajaybhatt.moviesapp.tools;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

public class Constants {


    public static final String API_BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String DB_NAME = "movies";

    public static String getMoviesApiKey(Context context) {

        Properties properties = new Properties();
        String key = "";
        try {
            properties.load(context.getAssets().open("keys.properties"));
            key = properties.getProperty("movies.key");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return key;
    }

}
