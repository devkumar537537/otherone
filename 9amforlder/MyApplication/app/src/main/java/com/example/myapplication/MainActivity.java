package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  TimePicker timePicker;
  CardView picktime_btn,movetodate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      bindview();
        Toast.makeText(this, "outpute"+getTimeofSystem(), Toast.LENGTH_SHORT).show();
movetodate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),DatepickerExample.class));
    }
});
        picktime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "output"+getTimeofSystem(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getTimeofSystem() {
        String timetext = " Current time : "+timePicker.getHour()+":"+timePicker.getMinute();
        return timetext;

    }

    private void bindview() {
       timePicker = findViewById(R.id.timepicker_componet);
       picktime_btn = findViewById(R.id.picktime_btn);
       movetodate = findViewById(R.id.datepicker_btn);

    }
}