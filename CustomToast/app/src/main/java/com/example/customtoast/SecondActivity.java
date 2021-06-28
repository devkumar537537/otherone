package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
String nametext;
int agevalue;

TextView nametextview,agetextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nametextview = findViewById(R.id.nametext);
        agetextview = findViewById(R.id.agetext);
        nametext = getIntent().getStringExtra("name");
        agevalue = getIntent().getIntExtra("imageid",12);


        nametextview.setText(nametext);
        agetextview.setText(agevalue+"");
    }
}