package com.example.servicesexamplse;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FirstServices extends Service {

    MediaPlayer mediaPlayer;
    boolean playing = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


        @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service is Created", Toast.LENGTH_SHORT).show();

        mediaPlayer = MediaPlayer.create(this,R.raw.mysone);
if(playing == false)
{
    mediaPlayer.start();
    playing = true;
}



        showtoast("Service Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        Toast.makeText(this, "Service STopped", Toast.LENGTH_SHORT).show();
showtoast("Service Destroyed");
if(mediaPlayer.isPlaying()  && playing == true)
{
    mediaPlayer.pause();
}else{
    mediaPlayer.start();
}

    }


    private void showtoast(String messaged)
    {
        Toast.makeText(this, "message"+messaged, Toast.LENGTH_SHORT).show();
    }
}
