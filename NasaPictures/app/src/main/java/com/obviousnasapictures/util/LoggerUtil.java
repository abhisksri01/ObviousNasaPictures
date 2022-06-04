package com.obviousnasapictures.util;

import android.util.Log;

import com.google.gson.Gson;


public class LoggerUtil {
    private static final String TAG = "OUTPUT";
    private static final String MY_TAG = "==============>";


    public static void logItem(Object src) {
        Gson gson = new Gson();
//        if (BuildConfig.DEBUG)
            Log.e(TAG, "====:> " + gson.toJson(src));
    }

    public static void log(String src) {
//        if (BuildConfig.DEBUG)
            Log.e(MY_TAG, "========>" + src);

    }

    public static void logError(String src) {
//        if (BuildConfig.DEBUG)
            Log.e(MY_TAG, "========>" + src);
    }
}