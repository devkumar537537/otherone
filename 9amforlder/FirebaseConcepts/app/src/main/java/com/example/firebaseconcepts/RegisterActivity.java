package com.example.firebaseconcepts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
EditText emailedit,passwordedit,nameedit,numberedit;
Button registerbtn;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
      bindview();
      firebaseAuth = FirebaseAuth.getInstance();

       registerbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emailt_text = emailedit.getText().toString().trim();
               String password_text = passwordedit.getText().toString().trim();
               String name_text = nameedit.getText().toString().trim();
               String number_text = numberedit.getText().toString().trim();
               progressBar.setVisibility(View.VISIBLE);
               createUser(emailt_text,password_text,name_text,number_text);
           }
       });
    }

    private void createUser(String emailt_text, String password_text, String name_text, String number_text) {
        firebaseAuth.createUserWithEmailAndPassword(emailt_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                  String userid = firebaseAuth.getCurrentUser().getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
                    HashMap<String,String> hashMap = new HashMap<>();

                    hashMap.put("userid",userid);
                    hashMap.put("email",emailt_text);
                    hashMap.put("name",name_text);
                    hashMap.put("password",password_text);
                    hashMap.put("number",number_text);
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(RegisterActivity.this,HomeActivitiy.class));
                                finish();
                            }else
                            {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void bindview() {
        emailedit = findViewById(R.id.registeremail);
        passwordedit = findViewById(R.id.registerpassword);
        nameedit = findViewById(R.id.registername);
        numberedit = findViewById(R.id.usernumber);
        registerbtn = findViewById(R.id.registerbtn);
        progressBar = findViewById(R.id.registerbasebar);
    }


}