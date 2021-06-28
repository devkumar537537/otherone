package com.example.sharepreferecnexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.value_ofkey);

        String vlaue =   SharedOperations.showvalue("email",getApplicationContext());
        textView.setText(vlaue);

    }
}