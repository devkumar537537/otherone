package com.example.bottomnavigationwithtabactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogOut extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        bottomNavigationView = findViewById(R.id.bottomanavigationveiw);
bottomNavigationView.setSelectedItemId(R.id.logout);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homtab:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case R.id.cattab:
                        startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                        break;
                    case R.id.logout:
                        Toast.makeText(getApplicationContext(),"Your are alread on logut",Toast.LENGTH_SHORT);
                        return true;
                }
                return false;
            }
        });
    }
}