package com.example.animationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button button,sequencebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.rotateimage);
        sequencebtn = findViewById(R.id.sequensbtn);
        textView = findViewById(R.id.textviewsequestion);

        sequencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequesion);
                textView.startAnimation(animation);
            }
        });
        button = findViewById(R.id.animationbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotationxml);
                imageView.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeinout);
//                imageView.startAnimation(animation);
            }
        });
    }
}