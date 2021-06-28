package com.example.service4pmexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundedService extends Service {
    private final IBinder myBinder = new MyLocalBinder();
    private static final String TAG = "MyBoundedService";
MediaPlayer mediaPlayer;
    private boolean res;

    public MyBoundedService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


//    public class MyLocalBinder extends Binder {
//        MyBoundService getService() {
//            return MyBoundService.this;
//        }
//    }
public String getCurrentTime() {
    SimpleDateFormat dateformat =
            new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                    Locale.US);
    return (dateformat.format(new Date()));

}

    public class  MyLocalBinder extends  Binder{
        MyBoundedService getService(){
            return  MyBoundedService.this;
        }
    }

    public void startmedia()
    {
        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<10;i++ ){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.e(TAG, "run: "+i);
                }

            }
        }).start();

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
