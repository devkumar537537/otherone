package com.example.bindingexample.apprepositry;

import android.util.Log;

import com.example.bindingexample.Serverlogic.ServerLogics;

public class Logirepo {
ServerLogics serverLogics = new ServerLogics();
    private static final String TAG = "Logirepo";
    public boolean logrepomethod(String email,String password){
boolean result = serverLogics.loginervermetho(email,password);
        Log.e(TAG, "logrepomethod: "+result );
        return result;
    }
}
