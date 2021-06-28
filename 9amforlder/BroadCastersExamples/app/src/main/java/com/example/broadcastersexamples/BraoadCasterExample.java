package com.example.broadcastersexamples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BraoadCasterExample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String actionString = intent.getAction();
        Toast.makeText(context,"action "+actionString,Toast.LENGTH_LONG).show();
    }
}
