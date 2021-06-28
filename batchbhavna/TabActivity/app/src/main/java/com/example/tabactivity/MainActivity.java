package com.example.tabactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Button openbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openbtn = findViewById(R.id.takeoutbottomsheet);

        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           BottomSheet bottomSheet = new BottomSheet();
           bottomSheet.show(getSupportFragmentManager(),"My first BottomSheet");
            }
        });

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        seticon();
    }

    private void seticon() {
        TextView textView =(TextView) LinearLayout.inflate(this,R.layout.tab,null);
        textView.setText("Home");
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.home,0,0,0);
        tabLayout.getTabAt(0).setCustomView(textView);


        TextView textView1 = (TextView) LinearLayout.inflate(this,R.layout.tab,null);
        textView1.setText("Chat");
        textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.chat,0,0,0);
        tabLayout.getTabAt(1).setCustomView(textView1);


        TextView textView2 = (TextView) LinearLayout.inflate(this,R.layout.tab,null);
        textView2.setText("User");
        textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.logout,0,0,0);
        tabLayout.getTabAt(2).setCustomView(textView2);
    }
}