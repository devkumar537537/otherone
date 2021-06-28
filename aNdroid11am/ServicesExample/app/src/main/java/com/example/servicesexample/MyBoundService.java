package com.example.servicesexample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {
   MediaPlayer mediaPlayer;

    private final IBinder myBinder = new MyLocalBinder();

boolean res =false;
String start;
    public MyBoundService() {
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action)) {
           startmedia();
        }
        else  if (AppConstant.STOP_ACTION.equals(action)) {
            Toast.makeText(this, "STOP CALLED", Toast.LENGTH_SHORT).show();
        }
//     if(intent.getExtras() != null){
//         String value = intent.getStringExtra("actions");
//         if(value.equals("start"))
//         {
//             startmedia();
//         }
//     }

        return myBinder;

    }



    public class MyLocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }
    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                        Locale.US);
        return (dateformat.format(new Date()));

    }
    public void startmedia()
    {
        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();

    }
    public void pausemedia()
    {
        if( res == true)
        {
            mediaPlayer.pause();
            res = false;
        }else
        {
           mediaPlayer.start();
            res = true;
        }
    }
    public void reset(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
