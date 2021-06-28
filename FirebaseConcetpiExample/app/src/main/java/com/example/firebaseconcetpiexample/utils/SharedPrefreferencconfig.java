package com.example.firebaseconcetpiexample.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefreferencconfig {
    public static final String MY_PREFERN_NAME = "com.example.firebaseconcetpiexample.utils";

    public static void savepassword(Context context, String value,String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERN_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getUserPassword(Context context,String key)
    {
        SharedPreferences sharedPreferences =  context.getSharedPreferences(MY_PREFERN_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"Nothing");
    }
}
