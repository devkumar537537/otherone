package com.example.service4pmexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyBoudndedActivity extends AppCompatActivity {
    TextView textView;
    Button btntime,pausebtn,resetbtn;

    boolean isBound;
    private MyBoundedService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_boudnded);
        textView = findViewById(R.id.timetextview);
        btntime = findViewById(R.id.showtimebtn);
        pausebtn = findViewById(R.id.pausebtn);
        resetbtn = findViewById(R.id.resetbtn);

//connection with activity of service
        Intent intent = new Intent(this, MyBoundedService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datetext = myService.getCurrentTime();

                textView.setText(datetext);
                myService.startmedia();
            }
        });

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.pausemedia();
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.reset();
            }
        });
    }
    //conncetcion with service with serviceconnection compoinent
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundedService.MyLocalBinder binder = (MyBoundedService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
            Toast.makeText(myService, "service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
            Toast.makeText(myService, "service false", Toast.LENGTH_SHORT).show();
        }
    };
}