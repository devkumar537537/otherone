package com.example.myfirebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity {

    EditText emailedikt,passwordeidt,confirmpassword,usernamedit;
    Button registebtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        connetcxml();
        firebaseAuth = FirebaseAuth.getInstance();
        registebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = emailedikt.getText().toString().trim();
                String password_text = passwordeidt.getText().toString().trim();
                 String username = usernamedit.getText().toString().trim();
                createaccoutn(email_text,password_text,username);
            }
        });
    }

    private void createaccoutn(String email_text, String password_text,String username) {

        firebaseAuth.createUserWithEmailAndPassword(email_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    String userid = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
                    HashMap<String ,String> hashMap = new HashMap<>();
                    hashMap.put("userid",userid);
                    hashMap.put("email",email_text);
                    hashMap.put("name",username);
                    hashMap.put("password",password_text);

//                    hashMap.put("password",password_text);
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                          if(task.isSuccessful())
                          {
                             startActivity(new Intent(CreateAccount.this,HomeActivity.class));
                          }else
                          {
                              Toast.makeText(CreateAccount.this,"registration failed",Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
                }else
                {
                    Toast.makeText(CreateAccount.this, "Problem in creating user", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void connetcxml() {
        emailedikt = findViewById(R.id.registermeilemail);
        passwordeidt = findViewById(R.id.registerpasswoerd);
        confirmpassword = findViewById(R.id.registerconfirempassword);
       usernamedit = findViewById(R.id.username);
        registebtn = findViewById(R.id.registerbtn);
    }
}