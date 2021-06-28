package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatepickerExample extends AppCompatActivity {
DatePicker datePicker;
CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker_example);

        datePicker = findViewById(R.id.datepicker_componet);
        cardView = findViewById(R.id.date_picker_btn);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DatepickerExample.this, "Date"+getUpdateDate(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUpdateDate() {
        String date_text = " Current date "+datePicker.getMonth()+1+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();
        return date_text;
    }
}