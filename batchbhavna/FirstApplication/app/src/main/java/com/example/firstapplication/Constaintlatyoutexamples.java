package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Constaintlatyoutexamples extends AppCompatActivity {

    TextView textView ,number;
    String emailvalue;
    int numbervalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constaintlatyoutexamples);
        textView = findViewById(R.id.textView2constaint);
        number = findViewById(R.id.textViewnumber);
        if(getIntent() != null)
        {
            emailvalue = getIntent().getStringExtra("email");
            numbervalue = getIntent().getIntExtra("number",67);

            textView.setText(emailvalue);
            number.setText(numbervalue+"");
        }
    }
}