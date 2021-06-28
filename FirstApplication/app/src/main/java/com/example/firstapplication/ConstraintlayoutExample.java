package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConstraintlayoutExample extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout_example);
        textView = findViewById(R.id.textoneconstaratin);
        String value = getIntent().getStringExtra("first");
        String number = getIntent().getStringExtra("number");
        String thirdvalue = value+ " and "+number;

        textView.setText(thirdvalue);
    }
}