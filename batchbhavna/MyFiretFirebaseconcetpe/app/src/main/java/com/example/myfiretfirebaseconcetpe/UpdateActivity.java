package com.example.myfiretfirebaseconcetpe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
Button updatebtn,deletebtn;
EditText namedit;
String userid;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updatebtn = findViewById(R.id.updatebtn);
        namedit = findViewById(R.id.enterpasswordupdate);
        deletebtn = findViewById(R.id.deletebtn);

        firebaseAuth = FirebaseAuth.getInstance();
        if(getIntent() != null)
        {
            userid = getIntent().getStringExtra("userid");
        }
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = namedit.getText().toString();

                updavalue(nametext);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData").child(userid);
              databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful())
                 {
                     DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("RegisterImage").child(userid);
                     databaseReference1.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(UpdateActivity.this, "User data success full removed", Toast.LENGTH_SHORT).show();
                        }
                         }
                     });
                 }
                  }
              });
            }
        });
    }

    private void updavalue(String nametext) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData").child(userid);

        Map<String,Object> hasmap = new HashMap<>();

        hasmap.put("name",nametext);
        databaseReference.updateChildren(hasmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(UpdateActivity.this, "Success full", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(UpdateActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}