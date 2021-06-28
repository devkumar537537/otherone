package com.example.myfiretfirebaseconcetpe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AfterNotificationActivity extends AppCompatActivity {
TextView textView;
String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_notification);
        textView = findViewById(R.id.infotext);

        if(getIntent() != null)
        {
           value = getIntent().getStringExtra("message") ;
        }

        textView.setText(value);
    }
}