package com.example.firebaseconcepts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotificationActivity extends AppCompatActivity {
TextView textVieww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textVieww = findViewById(R.id.textView);

        String value = getIntent().getStringExtra("message");
        textVieww.setText(value);
    }
}