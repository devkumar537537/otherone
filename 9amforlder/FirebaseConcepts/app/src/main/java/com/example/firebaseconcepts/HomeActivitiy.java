package com.example.firebaseconcepts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivitiy extends AppCompatActivity {

FirebaseAuth firebaseAuth;
ImageView fetchImage;
TextView emailview,nameview,numberview;
Button button;
ArrayList<ImageModel> imagelist;
ArrayList<UserModel> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activitiy);
        getuserlist();
        getimaglist();

button = findViewById(R.id.deletebtn);
        firebaseAuth = FirebaseAuth.getInstance();
        String userid = firebaseAuth.getCurrentUser().getUid();

                button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
        databaseReference1.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               startActivity(new Intent(HomeActivitiy.this,LogInActivity.class));
            }
        });
    }
});
        fetchImage = findViewById(R.id.fetchimage);
      emailview = findViewById(R.id.fetchemail);
        nameview = findViewById(R.id.fetchname);
        numberview = findViewById(R.id.fetchnumber);
        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);

                emailview.setText(userModel.getEmail());
                nameview.setText(userModel.getName());
                numberview.setText(userModel.getNumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivitiy.this, "error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Imagedata").child(userid);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ImageModel imageModel = snapshot.getValue(ImageModel.class);
                Glide.with(getApplicationContext()).load(imageModel.getImagurl()).into(fetchImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivitiy.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void getimaglist() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Imagedata");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imagelist.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    ImageModel imageModel = snapshot1.getValue(ImageModel.class);
                    imagelist.add(imageModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivitiy.this, "error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getuserlist() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userlist.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                    UserModel userModel = snapshot1.getValue(UserModel.class);
                    userlist.add(userModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivitiy.this, "error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homeoptionmenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.loguutbtn)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(HomeActivitiy.this,LogInActivity.class));
            finish();
        }else if(id == R.id.updateimagebtnmenu)
        {
            startActivity(new Intent(HomeActivitiy.this,UploadImage.class));
        }else if(id == R.id.updateprofile)
        {
            startActivity(new Intent(HomeActivitiy.this,UpdateProfile.class));
        }

        return super.onOptionsItemSelected(item);
    }
}