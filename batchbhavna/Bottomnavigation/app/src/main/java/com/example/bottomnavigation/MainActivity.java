package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation.fragmentst.ChatFragment;
import com.example.bottomnavigation.fragmentst.HomeFragment;
import com.example.bottomnavigation.fragmentst.LogoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
getsupporfragme(new HomeFragment());

        bottomNavigationView = findViewById(R.id.navigatonview);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.chat:
                        getsupporfragme(new ChatFragment());
                        break;
                    case R.id.logut:
                        getsupporfragme(new LogoutFragment());
                        break;
                    case R.id.homeid:
                        getsupporfragme(new HomeFragment());
                        break;

                }
                return true;
            }
        });
    }

    private void getsupporfragme(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
        fragmentTransaction.commit();

    }
}