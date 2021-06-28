package com.example.servicesexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServiceClass extends Service {
    MediaPlayer mediaPlayer;



    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service is Created", Toast.LENGTH_SHORT).show();

        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Service STopped", Toast.LENGTH_SHORT).show();

        mediaPlayer.stop();
    }




}
