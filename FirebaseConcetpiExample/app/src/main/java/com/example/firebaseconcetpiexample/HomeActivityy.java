package com.example.firebaseconcetpiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firebaseconcetpiexample.adpaters.RecyclerAdapters;
import com.example.firebaseconcetpiexample.models.ImageModel;
import com.example.firebaseconcetpiexample.models.Usermodel;
import com.example.firebaseconcetpiexample.utils.SharedPrefreferencconfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivityy extends AppCompatActivity {
    private static final String TAG = "HomeActivityy";
    FirebaseAuth firebaseAuth;
    TextView emailtext,numbertextview,ttitleview;
    ImageView profilepick;
    RecyclerView recyclerView;
    ArrayList<ImageModel> imagelist;
    ArrayList<Usermodel> userlist;
String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activityy);
        emailtext = findViewById(R.id.textView);
        numbertextview = findViewById(R.id.textView2);
        profilepick = findViewById(R.id.profilepick);
        recyclerView = findViewById(R.id.recyclerview);
        ttitleview = findViewById(R.id.textViewtitle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        userlist = new ArrayList<>();
        imagelist = new ArrayList<>();
        fetchimage();
        if(getIntent() != null  && getIntent().hasExtra("title")){

       value = getIntent().getStringExtra("title");

        }

     String value =    SharedPrefreferencconfig.getUserPassword(getApplicationContext(),"title");
        ttitleview.setText(value);
        firebaseAuth = FirebaseAuth.getInstance();
        String userid = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseReferencee = FirebaseDatabase.getInstance().getReference("Imagedata").child(userid);
        databaseReferencee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ImageModel imageModel = snapshot.getValue(ImageModel.class);
                Glide.with(getApplicationContext()).load(imageModel.getImagurl()).into(profilepick);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivityy.this, "errror "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usermodel usermodel = snapshot.getValue(Usermodel.class);
                emailtext.setText(usermodel.getEmail());
                numbertextview.setText(usermodel.getNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivityy.this, "errror "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void fetchimage() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Imagedata");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                    ImageModel imageModel = snapshot1.getValue(ImageModel.class);
                    imagelist.add(imageModel);
                }

                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("UserData");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1: snapshot.getChildren())
                        {
                            Usermodel imageModel = snapshot1.getValue(Usermodel.class);
                            userlist.add(imageModel);
                        }

                        RecyclerAdapters recyclerAdapters = new RecyclerAdapters(imagelist,userlist,getApplicationContext());
                        recyclerView.setAdapter(recyclerAdapters);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HomeActivityy.this, "eroror "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivityy.this, "eroror "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufirst,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout)
        {

            firebaseAuth.signOut();
            startActivity(new Intent(HomeActivityy.this,MainActivity.class));
            finish();
        }else if(item.getItemId() == R.id.movetoupload)
        {
            startActivity(new Intent(HomeActivityy.this,UploadImageActivity.class));
        }else if(item.getItemId() == R.id.mvoetopractic)
        {
            startActivity(new Intent(HomeActivityy.this,PlayGame.class));
        }else if(item.getItemId() == R.id.notifcation)
        {
            startActivity(new Intent(HomeActivityy.this,AndroidNotificiation.class));
        }
        return super.onOptionsItemSelected(item);
    }
}