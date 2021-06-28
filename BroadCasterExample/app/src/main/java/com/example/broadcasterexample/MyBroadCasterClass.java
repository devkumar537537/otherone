package com.example.broadcasterexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCasterClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String actionString = intent.getAction();


        if(actionString.equals("android.intent.action.AIRPLANE_MODE"))
        {
            Toast.makeText(context, "Alert Airplane crash", Toast.LENGTH_SHORT).show();
        }

    }
}
