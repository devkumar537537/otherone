package com.example.firebaseconcetpiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AfterNotification extends AppCompatActivity {
TextView textView;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_notification);
        textView = findViewById(R.id.aftertexteiw);
        if(getIntent() != null)
        {
             value = getIntent().getStringExtra("message");
        }
        textView.setText(value);
    }
}