package com.example.androitelephonymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {
    EditText receiveremail,subjecttext,mssage;
    Button sendemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sendemail = findViewById(R.id.send_email);
        receiveremail = findViewById(R.id.receiver_email);
        subjecttext = findViewById(R.id.email_subject);
        mssage = findViewById(R.id.email_body);

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = receiveremail.getText().toString().trim();
                String subject_text = subjecttext.getText().toString().trim();
                String message_text = mssage.getText().toString().trim();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ email_text});
                email.putExtra(Intent.EXTRA_SUBJECT, subject_text);
                email.putExtra(Intent.EXTRA_TEXT, message_text);
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });
    }
}