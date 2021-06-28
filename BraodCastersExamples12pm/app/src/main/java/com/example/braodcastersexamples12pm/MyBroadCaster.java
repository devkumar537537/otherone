package com.example.braodcastersexamples12pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCaster extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String actionString = intent.getAction();
        Toast.makeText(context,"action "+actionString,Toast.LENGTH_LONG).show();
        if(actionString.equals("android.intent.action.AIRPLANE_MODE"))
        {
            Toast.makeText(context, "You implemented global recevier", Toast.LENGTH_SHORT).show();
        }
    }
}
