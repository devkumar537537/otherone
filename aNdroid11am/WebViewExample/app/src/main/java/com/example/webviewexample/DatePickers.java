package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickers extends AppCompatActivity {
DatePicker datePicker;
Button button,showdatebtn;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pickers);

        button = findViewById(R.id.pickdate_btn);
        datePicker = findViewById(R.id.datepciker);
        textView = findViewById(R.id.date_textview);
        showdatebtn = findViewById(R.id.showdatebox_btn);

        showdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                showdatebtn.setVisibility(View.GONE);
            }
        });
        textView.setText("Current Date"+getCureentDate());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("Update Date "+getCureentDate());
                datePicker.setVisibility(View.GONE);
                showdatebtn.setVisibility(View.VISIBLE);
            }
        });

    }

    private String getCureentDate() {
        int month = datePicker.getMonth() + 1;
        String date = " "+datePicker.getDayOfMonth()+" / "+month+" / "+datePicker.getYear();
     return date;
    }
}