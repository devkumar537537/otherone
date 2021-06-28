package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView numberview,nmaeview;
    String numbervaluie,namevalule,emailvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        numberview = findViewById(R.id.number_view);
        nmaeview = findViewById(R.id.nameview);

        numbervaluie = getIntent().getStringExtra("number");
        namevalule = getIntent().getStringExtra("name");
       emailvalue = getIntent().getStringExtra("email");


        numberview.setText(numbervaluie);
        nmaeview.setText(namevalule);
    }
}