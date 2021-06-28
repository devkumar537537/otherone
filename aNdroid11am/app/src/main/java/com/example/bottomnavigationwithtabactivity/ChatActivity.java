package com.example.bottomnavigationwithtabactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ChatActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tabLayout =findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);
        seticon();


        bottomNavigationView = findViewById(R.id.bottomanavigationveiw);
 bottomNavigationView.setSelectedItemId(R.id.cattab);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homtab:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case R.id.cattab:
                        Toast.makeText(getApplicationContext(),"Your are alread on Chat",Toast.LENGTH_SHORT);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),LogOut.class));
                        break;
                }
                return true;
            }
        });


    }

    private void seticon() {
        TextView textView = (TextView) LinearLayout.inflate(this,R.layout.tabs,null);
        textView.setText("Home");
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.home,0,0,0);
        tabLayout.getTabAt(0).setCustomView(textView);

        TextView textView1 = (TextView) LinearLayout.inflate(this,R.layout.tabs,null);
        textView1.setText("Chat");
        textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.chat,0,0,0);
        tabLayout.getTabAt(1).setCustomView(textView1);


        TextView textView2 = (TextView) LinearLayout.inflate(this,R.layout.tabs,null);
        textView2.setText("User");
        textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.user,0,0,0);
        tabLayout.getTabAt(2).setCustomView(textView2);
    }
}