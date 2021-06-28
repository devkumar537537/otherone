package com.example.localandroidnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button;
TextView textView;
    private static final String CHANNEL_ID = "technicalGuyes";
    CharSequence name = "my_channel";
    String Description = "This is my channel";
    private final int NOTIFICATION_ID = 201;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.noatificationetext);

        if(getIntent() != null  && getIntent().hasExtra("title")){

            for(String key: getIntent().getExtras().keySet())
            {
                Log.e(TAG, "onCreate: key :  "+key+" data is " + getIntent().getExtras().getString(key) );
                textView.append(getIntent().getExtras().getString(key) + "\n");
            }
        }

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful())
                        {
                            String token = task.getResult().getToken();
                            Log.d("Token","OnComplete : => "+token);
                            textView.append(token);
                        }else
                        {
                            Toast.makeText(MainActivity.this, "exception"+task.getException() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }

    private void addNotification() {
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
        Intent yesReceive = new Intent();
        yesReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getService(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.addAction(R.drawable.back_dialog, "Yes", pendingIntentYes);
        Intent notificationIntent = new Intent(MainActivity.this, NotificationActivity.class);

//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        notificationIntent.putExtra("message", "This is a notification message");
//
////        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, notificationIntent,
////                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
//                123,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        notificationManager.notify(123,builder.build());

    }
}