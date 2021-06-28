package com.example.contentproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sms extends AppCompatActivity {
    EditText numberedit,messageedit;
    Button sendsmsbtn;
    int SMS_REQUEST = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        numberedit = findViewById(R.id.user_sms_number);
        messageedit = findViewById(R.id.sms_body);
        sendsmsbtn = findViewById(R.id.send_sms_btn);

        sendsmsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_text = numberedit.getText().toString().trim();
                String message_text = messageedit.getText().toString().trim();

                Intent smsintent = new Intent(getApplicationContext(),Sms.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),SMS_REQUEST,smsintent,0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number_text,null,message_text,pendingIntent,null);

                Toast.makeText(getApplicationContext(), "Messages Send Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}