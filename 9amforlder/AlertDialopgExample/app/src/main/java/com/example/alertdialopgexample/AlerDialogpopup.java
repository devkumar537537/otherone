package com.example.alertdialopgexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlerDialogpopup extends AppCompatActivity {
Button movetofirst;
AlertDialog.Builder alertdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aler_dialogpopup);
        alertdialog = new AlertDialog.Builder(this);

        movetofirst = findViewById(R.id.backbtn);
        movetofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialog.setMessage("Are you sure to go back?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AlerDialogpopup.this,MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlerDialogpopup.this, "You choose No", Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialogone = alertdialog.create();
                alertDialogone.setTitle("About Activity");
                alertDialogone.show();


            }
        });
    }
}