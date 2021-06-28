package com.example.andridnotification;

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
import com.google.firebase.messaging.FirebaseMessaging;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button;
    TextView textView;
    private static final String CHANNEL_ID = "technicalGuyes";
    CharSequence name = "my_channel";
    String Description = "This is my channel";
    private final int NOTIFICATION_ID = 201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.dataservertext);

        if(getIntent() != null  && getIntent().hasExtra("title")){

            for(String key: getIntent().getExtras().keySet())
            {
                Log.e(TAG, "onCreate: key :  "+key+" data is " + getIntent().getExtras().getString(key) );
                textView.append(getIntent().getExtras().getString(key) + "\n");
            }
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
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
    }

    private void addNotification() {

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

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
                        .setSmallIcon(android.R.drawable.star_on) //set icon for notification
                        .setContentTitle("Android") //set title of notification
                        .setContentText("This is message of notification")//this is notification message
                        .setAutoCancel(true)// makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Intent notificationIntent = new Intent(MainActivity.this, NotificationViewActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(pendingIntent);

        notificationManager.notify(12,builder.build());
    }
}