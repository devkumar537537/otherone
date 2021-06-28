package com.example.mytelephonymanagere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText numbertext;
    Button callbtn,movetosms,movetoemail,sharebtn;
    String[] perm = {Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS};
    int CALL_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbtn = findViewById(R.id.sendCall);
        numbertext = findViewById(R.id.usern_number);
        movetosms=findViewById(R.id.move_t0_sms);
        movetoemail=findViewById(R.id.move_to_email);
        sharebtn = findViewById(R.id.move_to_share);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numbertext.getText().toString().trim();
                //  String numberr = "7009125438";
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(perm,CALL_REQUEST);
                } else {
                    Intent callintent = new Intent(Intent.ACTION_CALL);
                    callintent.setData(Uri.parse("tel:"+number));
                    startActivity(callintent);
                }
            }
        });
        movetosms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SMSACtivity.class));
            }
        });

        movetoemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EmailActivity.class));
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numbertext.getText().toString().trim();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,number);
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent,"Share via");
                startActivity(sendIntent);
            }
        });
    }
}