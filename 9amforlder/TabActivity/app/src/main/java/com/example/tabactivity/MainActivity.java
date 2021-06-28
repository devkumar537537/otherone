package com.example.tabactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
ViewPager viewPager;
TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);
        seticon();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void seticon() {
        TextView textView =(TextView) LinearLayout.inflate(this,R.layout.tab_textlayout,null);
        textView.setText("Chat");
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.chat,0,0,0);
        tabLayout.getTabAt(0).setCustomView(textView);

        TextView textView1 =(TextView) LinearLayout.inflate(this,R.layout.tab_textlayout,null);
        textView1.setText("User");
        textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.user,0,0,0);
        tabLayout.getTabAt(1).setCustomView(textView1);
    }
}