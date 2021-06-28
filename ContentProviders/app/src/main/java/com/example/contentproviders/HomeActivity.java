package com.example.contentproviders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    EditText numbertext;
    Button callbtn,movetosms,movetoemail,sharebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        callbtn = findViewById(R.id.sendCall);
        numbertext = findViewById(R.id.usern_number);
        movetosms=findViewById(R.id.move_t0_sms);
        movetoemail=findViewById(R.id.move_to_email);
        sharebtn = findViewById(R.id.move_to_share);
        movetosms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,Sms.class));
            }
        });
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numbertext.getText().toString().trim();




                    Intent callintent = new Intent(Intent.ACTION_CALL);

                    callintent.setData(Uri.parse("tel:"+number));
                    callintent.putExtra("simSlot", 1);

                    startActivity(callintent);

            }
        });
    }
}