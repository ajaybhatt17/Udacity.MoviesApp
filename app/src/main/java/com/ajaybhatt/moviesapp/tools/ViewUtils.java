package com.ajaybhatt.moviesapp.tools;

import android.content.Context;
import android.widget.Toast;

public class ViewUtils {

    public static void showToast(Context context, String message) {
        if(message!=null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
