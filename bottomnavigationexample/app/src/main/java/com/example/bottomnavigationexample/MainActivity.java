package com.example.bottomnavigationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigationexample.fragmentst.Chat;
import com.example.bottomnavigationexample.fragmentst.FirstFragment;
import com.example.bottomnavigationexample.fragmentst.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentstart(new Home());
        bottomNavigationView = findViewById(R.id.bottomanavigationveiw);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.homtab:
                        fragmentstart(new Home());
                        break;
                    case R.id.cattab:
                        fragmentstart(new Chat());
                        break;
                    case R.id.logout:
                        fragmentstart(new FirstFragment());
                        break;


                }
                return true;
            }
        });
    }

    private void fragmentstart(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framcontainer,fragment);
        fragmentTransaction.commit();

    }


}