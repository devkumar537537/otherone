package com.example.servicesexamplse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button startbtn,stopbtn,pausebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn = findViewById(R.id.startservice);
        stopbtn = findViewById(R.id.stopservie);
       pausebtn = findViewById(R.id.puseservice);
       pausebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startService(new Intent(MainActivity.this,FirstServices.class));
           }
       });
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startService(new Intent(MainActivity.this,FirstServices.class));
            }
        });


        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,FirstServices.class));
            }
        });
    }
}