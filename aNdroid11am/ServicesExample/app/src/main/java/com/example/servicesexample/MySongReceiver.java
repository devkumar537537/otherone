package com.example.servicesexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class MySongReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;
    private static final String TAG = "MySongReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra("right");
        if(text.equals("right"))
        {
          mediaPlayer = MediaPlayer.create(context,R.raw.song);
          mediaPlayer.start();
        }
        Toast.makeText(context, "msg "+text, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: "+text);
    }
}
