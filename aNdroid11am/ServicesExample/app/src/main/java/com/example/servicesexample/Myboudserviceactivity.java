package com.example.servicesexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Myboudserviceactivity extends AppCompatActivity {
TextView textView;
Button btntime,pausebtn,resetbtn;
MyBoundService myService;
boolean isBound;

    private static final String CHANNEL_ID = "technicalGuyes";
    CharSequence name = "my_channel";
    String Description = "This is my channel";
    private final int NOTIFICATION_ID = 201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myboudserviceactivity);
        textView = findViewById(R.id.timetextview);
        btntime = findViewById(R.id.showtimebtn);
        pausebtn = findViewById(R.id.pausebtn);
        resetbtn = findViewById(R.id.resetbtn);
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
if(getIntent() != null)
{
String text = getIntent().getStringExtra("right");

    Toast.makeText(this, "messgage "+text, Toast.LENGTH_SHORT).show();
}




        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound == true)
                {
                    createnotifcaiont();
                }


         }
        });
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.pausemedia();
                String currentTime = myService.getCurrentTime();
                textView.setText(currentTime);
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.reset();
            }
        });
    }

    private void createnotifcaiont() {
        NotificationManager notificationManager =  (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            mChannel.setDescription(Description);
            mChannel.enableLights(true);

            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);

            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.stat_notify_more) //set icon for notification
                        .setContentTitle("Android") //set title of notification
                        .setContentText("This is message of notification")//this is notification message
                        .setAutoCancel(true)// makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent = new Intent(this, MySongReceiver.class);
        intent.putExtra("right", "right" ); //If you wan to send data also
        PendingIntent pIntent = PendingIntent.getActivity(this,23, intent, 0); //<notification_id> may be any number

        builder
                .addAction(android.R.drawable.ic_btn_speak_now, "No", pIntent)
                .addAction(android.R.drawable.ic_delete, "Yes", pIntent);

        notificationManager.notify(123,builder.build());
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MyBoundService.MyLocalBinder binder = (MyBoundService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
            Toast.makeText(myService, "service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            Toast.makeText(myService, "service false", Toast.LENGTH_SHORT).show();
        }
    };

}