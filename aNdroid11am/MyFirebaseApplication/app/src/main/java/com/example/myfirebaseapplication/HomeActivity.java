package com.example.myfirebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

FirebaseAuth firebaseAuth;
TextView email,password,name,uid;
DatabaseReference databaseReference;
ImageView userimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindview();

        firebaseAuth = FirebaseAuth.getInstance();
        String userid = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Itemmodel itemmodel = snapshot.getValue(Itemmodel.class);
                email.setText(itemmodel.getEmail());
                password.setText(itemmodel.getPassword());
                uid.setText(itemmodel.getUserid());
                name.setText(itemmodel.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Imagedata").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ImageModelClass imageModelClass = snapshot.getValue(ImageModelClass.class);
                Glide.with(getApplicationContext()).load(imageModelClass.getImagurl()).into(userimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logoutnbtn)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(HomeActivity.this,LogInActivityStart.class));
            finish();
        }else if(id == R.id.addPost)
        {
            startActivity(new Intent(HomeActivity.this,UploadPost.class));

        }
        return super.onOptionsItemSelected(item);
    }

    private void bindview() {
        email = findViewById(R.id.reademail);
        password = findViewById(R.id.readpassword);
        name = findViewById(R.id.readname);
        uid = findViewById(R.id.readid);
        userimage = findViewById(R.id.fetchimage);
    }
}