package com.example.parallaxeffect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parallaxeffect.fragments.FirstFragment;
import com.example.parallaxeffect.fragments.SecondFragment;
import com.example.parallaxeffect.fragments.ThirdFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    Button takeoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         connectxml();

         takeoutbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 BottomSheetDialog bottomSheet = new BottomSheetDialog();


                 bottomSheet.show(getSupportFragmentManager(),
                         "ModalBottomSheet");
             }
         });

        ViewPageAdapter viewPageAdapter = connectframgnet();
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
                    case 3:
                        new FirstFragment();
                        break;
                    case 4:
                        new SecondFragment();
                        break;
                    case 5:
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

    private ViewPageAdapter connectframgnet() {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new FirstFragment(),"First");
        viewPageAdapter.addFragment(new SecondFragment(),"Second");
        viewPageAdapter.addFragment(new ThirdFragment(),"Third");
        viewPageAdapter.addFragment(new FirstFragment(),"First");
        viewPageAdapter.addFragment(new SecondFragment(),"Second");
        viewPageAdapter.addFragment(new ThirdFragment(),"Third");
        return viewPageAdapter;
    }

    private void connectxml() {
        takeoutbtn = findViewById(R.id.putlloutbtnbottom);
        tabLayout = findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.home_toolbar);
        viewPager = findViewById(R.id.viewpage);
    }
}