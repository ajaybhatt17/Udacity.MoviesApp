package com.ajaybhatt.moviesapp.tools;

import android.content.Context;

import com.google.gson.Gson;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseSource {

    public static void putBoolean(Context context, String key, boolean value) {
        try {
            DB snappydb = DBFactory.open(context, Constants.DB_NAME);
            snappydb.putBoolean(key, value);
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static void putArray(Context context, String key, Object[] array) {
        try {
            DB snappydb = DBFactory.open(context, Constants.DB_NAME);
            snappydb.put(key, array);
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static boolean getBoolean(Context context, String key) {
        boolean value = false;
        try {
            DB snappydb = DBFactory.open(context, Constants.DB_NAME);
            value = snappydb.getBoolean(key);
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <Any> List<Any> getObjects(Context context, String key, Class className) {
        List<Any> favouriteMovieList = new ArrayList<>();
        try {
            DB snappydb = DBFactory.open(context, Constants.DB_NAME);
            List<String> favouriteMovies = new ArrayList<String>(Arrays.asList(snappydb.getArray(key, String.class)));
            Gson gson = new Gson();
            for (String str : favouriteMovies) {
                favouriteMovieList.add((Any) gson.fromJson(str, className));
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return favouriteMovieList;
    }



}
