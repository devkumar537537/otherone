package com.example.newfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       System.out.println("OnCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(tag,"OnDestroy Method");
        System.out.println("OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(tag,"OnStart Method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(tag,"OnResume Method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(tag,"OnRestart Method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(tag,"OnPause Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(tag,"OnStop Method");
    }
}
