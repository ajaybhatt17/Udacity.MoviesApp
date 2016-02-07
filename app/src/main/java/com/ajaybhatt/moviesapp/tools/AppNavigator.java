package com.ajaybhatt.moviesapp.tools;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ajaybhatt.moviesapp.models.VideoResult;

public class AppNavigator {

    public static void navigateToTrailer(Context context, VideoResult trailerModel) {
        if (trailerModel.getSite().equals("YouTube")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerModel.getKey()));
                context.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailerModel.getKey()));
                context.startActivity(intent);
            }
        }
    }

}
