package com.example.animationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView fadinimage,rotationimage;
Button fadinbtn,rotationbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fadinbtn = findViewById(R.id.fadeinanimationbtn);
        fadinimage = findViewById(R.id.fadeinimage);

        rotationbtn = findViewById(R.id.rotaionbtn);
        rotationimage = findViewById(R.id.rotationimage);
        Animation animation = new TranslateAnimation(0, 500,0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        rotationbtn.startAnimation(animation);
     rotationbtn.setVisibility(View.VISIBLE);
        rotationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);

                rotationimage.startAnimation(animation1);
            }
        });
        fadinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);

                fadinimage.startAnimation(animation);
            }
        });
    }
}