package com.example.gus.places.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Laotshi on 11/16/15.
 */
public class Utils {

    /**
     * Use this method to validate if the Network is available
     * @param context represent the main activity
     * @return void.
     */
    public static boolean isNetworkAvailable (Context context){
        boolean isAvailable = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            isAvailable = true;

        return isAvailable;
    }


    /**
     * Use this method to hide the Keyboard
     *
     * @param activity represent the context that you want to hide the keyboard.
     * @return void.
     */
    public static void hideKeyboard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null){
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
