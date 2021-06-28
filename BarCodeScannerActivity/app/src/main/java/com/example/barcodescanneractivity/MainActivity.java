package com.example.barcodescanneractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button movetoBarCode,movetogeneratecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movetoBarCode = findViewById(R.id.btnScanBarCode);
       movetogeneratecode = findViewById(R.id.moveto_generate);
       movetogeneratecode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,GenerateBarcode.class));
           }
       });
        movetoBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BarCodeScannerActivity.class));
            }
        });
    }
}