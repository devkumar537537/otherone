package com.example.localandroidnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
String notificatvalue;
TextView notificationtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationtextview = findViewById(R.id.notifcationtext);
        if(getIntent() != null)
        {
            notificatvalue = getIntent().getStringExtra("message");
            notificationtextview.setText(notificatvalue);
        }else
        {
            notificationtextview.setText("No data from notification");
        }
    }
}