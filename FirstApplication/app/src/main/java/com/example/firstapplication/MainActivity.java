package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstapplication.fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {
Button loginbtn;
EditText emailedit,numberedit;

String tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   loginbtn = findViewById(R.id.loginbtn);
emailedit = findViewById(R.id.email);
numberedit = findViewById(R.id.number);

   loginbtn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           String email_text = emailedit.getText().toString().trim();
           String numberedit_text = numberedit.getText().toString().trim();
           Intent intent = new Intent(MainActivity.this,ConstraintlayoutExample.class);
           intent.putExtra("first",email_text);
           intent.putExtra("number",numberedit_text);
           startActivity(intent);
       }
   });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentcontainer,new FirstFragment());
        fragmentTransaction.commit();
    }


}