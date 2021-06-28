package com.example.animationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
ImageView ball,fadanimation;
Button fadeinbtn,applybtn;
TextView textView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coneectxml();
        fadeinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
//                fadinimage.startAnimation(animation);

//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomeffect);
//                ball.startAnimation(animation);
            }
        });

        ball.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    AnimationDrawable animation = (AnimationDrawable) ball.getDrawable();
                    animation.stop();
                    animation.selectDrawable(0);
                    animation.start();
                    return true;
                }

                return false;
            }
        });

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //              Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
//                fadinimage.startAnimation(animation);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
                fadanimation.startAnimation(animation);
            }
        });
    }

    private void coneectxml() {
        ball = findViewById(R.id.imageview);
        fadeinbtn = findViewById(R.id.applyanmation);
        textView = findViewById(R.id.textview);
        applybtn = findViewById(R.id.nextanimation);
        fadanimation = findViewById(R.id.imageviewsecond);
    }
}