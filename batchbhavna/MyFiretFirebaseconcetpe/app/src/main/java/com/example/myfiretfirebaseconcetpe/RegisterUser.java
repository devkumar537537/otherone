package com.example.myfiretfirebaseconcetpe;

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

public class RegisterUser extends AppCompatActivity {
EditText emailedi,passwordedit,numberedit,nameedit;
Button registerbtn;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        conncextml();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData");
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = emailedi.getText().toString().trim();
                String password_text = passwordedit.getText().toString().trim();
                String numbereditext = numberedit.getText().toString().trim();
                String nametext = nameedit.getText().toString().trim();
              progressBar.setVisibility(View.VISIBLE);
                createuser(emailtext,password_text,numbereditext,nametext);
            }
        });
    }

    private void createuser(String emailtext, String password_text, String numbereditext, String nametext) {
firebaseAuth.createUserWithEmailAndPassword(emailtext,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        String userid = firebaseAuth.getCurrentUser().getUid();
   if(task.isSuccessful()){
       HashMap<String,String> userdata = new HashMap<>();

       userdata.put("email",emailtext);
       userdata.put("password",password_text);
       userdata.put("number",numbereditext);
       userdata.put("name",nametext);
       userdata.put("userid",userid);
       databaseReference.child(userid).setValue(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(RegisterUser.this, "Register Succeessflully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(RegisterUser.this,HomeActivity.class));
                   finish();
               }else
               {
                 progressBar.setVisibility(View.GONE);
                   Toast.makeText(RegisterUser.this, "Registeration Failed "+task.getException(), Toast.LENGTH_SHORT).show();

               }
           }
       });

   }
    }
});

    }

    private void  conncextml(){
        emailedi =findViewById(R.id.emaileditregister);
        passwordedit = findViewById(R.id.passwordeditregister);
        numberedit = findViewById(R.id.numbereditregister);
        nameedit = findViewById(R.id.numbereditregister);
        registerbtn = findViewById(R.id.regsiterbtn);
        progressBar = findViewById(R.id.progressbar);
    }
}