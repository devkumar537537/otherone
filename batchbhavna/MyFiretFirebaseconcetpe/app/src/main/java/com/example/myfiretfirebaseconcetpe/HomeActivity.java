package com.example.myfiretfirebaseconcetpe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfiretfirebaseconcetpe.adapters.UserAdapter;
import com.example.myfiretfirebaseconcetpe.models.ImageModel;
import com.example.myfiretfirebaseconcetpe.models.Models;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
FirebaseAuth firebaseAuth;
TextView emailview,nameview,numberview;
RecyclerView recyclerView;
ArrayList<Models> arrayList;
ArrayList<ImageModel> imagleislt;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        connectxmla();
        arrayList = new ArrayList<>();
        imagleislt = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
          imageView = findViewById(R.id.profileimage);
        fetchdata();
        firebaseAuth = FirebaseAuth.getInstance();

String userid = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData").child(userid);

     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             if(snapshot.exists())
             {
                 if(snapshot.hasChild("userid"))
                 {
                     Models models = snapshot.getValue(Models.class);
                     emailview.setText(models.getEmail());
                     nameview.setText(models.getName());
                     numberview.setText(models.getNumber());

//             String passwordvalue = snapshot.child("password").getValue().toString();
//emailview.setText(passwordvalue);
                 }

             }



         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
             Toast.makeText(HomeActivity.this, "error "+error.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });
DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("RegisterImage").child(userid);
databaseReference1.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists())
        {
            if(snapshot.hasChild("userid"))
            {
                ImageModel imageModel = snapshot.getValue(ImageModel.class);

                Glide.with(getApplicationContext()).load(imageModel.getImageurl()).into(imageView);
            }

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }

    private void fetchdata() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Models models= dataSnapshot.getValue(Models.class);
                        arrayList.add(models);

                    }

                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("RegisterImage");
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            imagleislt.clear();
                            if(snapshot.exists())
                            {
                                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                                {
                                    ImageModel imageModel = dataSnapshot.getValue(ImageModel.class);
                                    imagleislt.add(imageModel);
                                }

                                UserAdapter userAdapter = new UserAdapter(arrayList,getApplicationContext(),imagleislt);
                                recyclerView.setAdapter(userAdapter);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectxmla() {
        emailview = findViewById(R.id.emailtextview);
        nameview = findViewById(R.id.namelayout);
        numberview = findViewById(R.id.numberlayout);
        recyclerView = findViewById(R.id.userrecyclerview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuresorce,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            finish();
        }else if(item.getItemId() == R.id.uploadimage)
        {
            startActivity(new Intent(HomeActivity.this,UploadImage.class));
        }else if(item.getItemId() == R.id.producenotifcation)
        {
            startActivity(new Intent(HomeActivity.this,NotificationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}