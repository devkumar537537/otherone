package com.example.firebaseconcetpiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseconcetpiexample.utils.SharedPrefreferencconfig;

public class AndroidNotificiation extends AppCompatActivity {
Button button;
    private static final String CHANNEL_ID = "technicalGuyes";
    CharSequence name = "my_channel";
    String Description = "This is my channel";
    private final int NOTIFICATION_ID = 201;

TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_notificiation);
        button = findViewById(R.id.producenotirfication);

textView = findViewById(R.id.viewtext);

       String value = SharedPrefreferencconfig.getUserPassword(getApplicationContext(),"title");
        textView.setText(value);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotifcation();
            }
        });
    }

    private void addNotifcation() {
        NotificationManager notificationManager =  (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            mChannel.setDescription(Description);
            mChannel.enableLights(true);

            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mChannel.setShowBadge(false);

            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.stat_notify_more) //set icon for notification
                        .setContentTitle("Android") //set title of notification
                        .setContentText("This is message of notification")//this is notification message
                        .setAutoCancel(true)// makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent notificationIntent = new Intent(AndroidNotificiation.this, AfterNotification.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                123,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        notificationManager.notify(123,builder.build());


    }
}