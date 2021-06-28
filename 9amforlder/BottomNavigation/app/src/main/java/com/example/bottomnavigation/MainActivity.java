package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   bottomNavigationView = findViewById(R.id.bottom_navigation);
        AppBarConfiguration   appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,R.id.chatFragment,R.id.userFragment
        ).build();

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_container);
  NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);


    }
}