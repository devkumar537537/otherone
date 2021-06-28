package com.example.spinnerexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialExamples extends AppCompatActivity {
Button button;
AlertDialog.Builder builder;


DatePicker datePicker;
TextView datetexbivew;
Button datepickerbtn;
    String datetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dial_examples);
        button = findViewById(R.id.showalert);

        datePicker = findViewById(R.id.datepicker);
        datetexbivew = findViewById(R.id.datetextview);
        datepickerbtn = findViewById(R.id.datepickerbtn);


        datetexbivew.setText("Current Date"+getCurrentDate());

        datepickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetexbivew.setText("Update Date"+getCurrentDate());
                Toast.makeText(AlertDialExamples.this, "New Selected Data "+datetext, Toast.LENGTH_SHORT).show();
            }
        });
        builder = new AlertDialog.Builder(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to popup Message ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(AlertDialExamples.this, "You click yes action", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(AlertDialExamples.this, "You click No action", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(android.R.drawable.star_on);
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("AlerDialogExample");
                alertDialog.show();
            }
        });
    }

    private String getCurrentDate() {

        StringBuilder builder = new StringBuilder();

        builder.append(datePicker.getDayOfMonth() +" /");
        builder.append((datePicker.getMonth() + 1)+" /");
        builder.append(datePicker.getYear());
        datetext = builder.toString();


        return builder.toString();
    }
}