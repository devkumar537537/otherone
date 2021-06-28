package com.example.sharepreferecnexample;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedOperations {
    public static final String MY_PREFERN_NAME = "com.example.sharepreferecnexample";

    public static void insertvalue(String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERN_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key,value);
        editor.apply();

    }

    public static String showvalue(String key,Context context) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences(MY_PREFERN_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"Nothing");
    }

    public static void deletekey(String key,Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(MY_PREFERN_NAME, Context.MODE_PRIVATE);
        settings.edit().remove(key).commit();
    }
    }
