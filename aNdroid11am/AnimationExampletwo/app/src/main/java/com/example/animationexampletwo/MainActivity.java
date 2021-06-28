package com.example.animationexampletwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView fadinimage;
    Button fadeinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fadinimage = findViewById(R.id.fadeinimaget);
        fadeinbtn = findViewById(R.id.fadeinanimationbtnt);


        fadeinbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        fadinimage.startAnimation(animation);
    }
});

    }




}