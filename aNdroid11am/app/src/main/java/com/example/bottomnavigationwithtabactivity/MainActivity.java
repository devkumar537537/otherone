package com.example.bottomnavigationwithtabactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomanavigationveiw);
bottomNavigationView.setSelectedItemId(R.id.homtab);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homtab:
                        Toast.makeText(getApplicationContext(),"Your are alread on home",Toast.LENGTH_SHORT);
                       return true;
                    case R.id.cattab:
                        startActivity(new Intent(getApplicationContext(),ChatActivity.class));

                        break;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),LogOut.class));
                        break;
                }
                return false;
            }
        });
    }
}