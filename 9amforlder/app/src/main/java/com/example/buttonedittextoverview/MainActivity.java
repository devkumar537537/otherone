package com.example.buttonedittextoverview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText emailedit,passwordedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();

      button.setText(R.string.submit);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String email_text = emailedit.getText().toString();
              String password_text = passwordedit.getText().toString();

              if(TextUtils.isEmpty(email_text))
              {
                  Toast.makeText(MainActivity.this,"email empty",Toast.LENGTH_LONG).show();
              }else if(TextUtils.isEmpty(password_text))
              {
                  Toast.makeText(MainActivity.this,"Password empty",Toast.LENGTH_LONG).show();
              }else
              {
//                  showtoast(email_text,password_text);
                  shotoatagain(email_text,password_text);
              }


          }
      });
    }

//    private void showtoast(String email_text, String password_text) {
//
//        Toast.makeText(MainActivity.this,"email: "+email_text+" password: "+password_text,Toast.LENGTH_SHORT).show();
//    }
    private  void shotoatagain(String email,String password)
    {
        Toast.makeText(MainActivity.this,"email: "+email+" password: "+password,Toast.LENGTH_SHORT).show();
    }

    private void bindview() {
        button = findViewById(R.id.submint_btn);
        emailedit = findViewById(R.id.userm_email);
        passwordedit = findViewById(R.id.user_password);
    }
}