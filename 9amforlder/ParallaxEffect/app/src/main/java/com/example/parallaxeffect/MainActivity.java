package com.example.parallaxeffect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
Toolbar toolbar;
ViewPager viewPager;
FirstFragment firstFragment = new FirstFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.home_toolbar);
        viewPager = findViewById(R.id.viewpage);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(firstFragment,"First");
        viewPageAdapter.addFragment(new SecondFragment(),"Second");
        viewPageAdapter.addFragment(new ThirdFragment(),"Third");

        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition())
                {
                    case 0:
                        new FirstFragment();
                        break;
                    case 1:
                        new SecondFragment();
                        break;
                    case 2:
                        new ThirdFragment();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}