package com.example.firebaseconcetpiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateInfoActiity extends AppCompatActivity {
EditText inofeedit;
Button updatebtn,revmobebtn;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_actiity);
        inofeedit = findViewById(R.id.infolayout);
        updatebtn = findViewById(R.id.updatebtn);
        progressBar = findViewById(R.id.updateprogress);
        revmobebtn = findViewById(R.id.removebtn);

        firebaseAuth = FirebaseAuth.getInstance();

       // String userid = firebaseAuth.getCurrentUser().getUid();
        if(getIntent() != null)
        {

            userid = getIntent().getStringExtra("userid");
        }
        revmobebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Imagedata").child(userid);
                            databaseReference1.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()) {
                                        Toast.makeText(UpdateInfoActiity.this, "Value deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String inofoetext = inofeedit.getText().toString().trim();

                Map<String,Object> hashmap = new HashMap<>();
                hashmap.put("number",inofoetext);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
                databaseReference.updateChildren(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(UpdateInfoActiity.this, "Success fully updated", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(UpdateInfoActiity.this, "eroror "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}