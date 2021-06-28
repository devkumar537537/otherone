package com.example.getureexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
@SuppressLint("ClickableViewAccessibility")
public class SecondActivit extends AppCompatActivity {
ImageView imageView;
    private static final String DEBUG_TAG= "SecondActivit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        Log.e(DEBUG_TAG,"Action was DOWN");
                        return true;
                    case (MotionEvent.ACTION_MOVE) :
                        Log.e(DEBUG_TAG,"Action was MOVE");
                        return true;
                    case (MotionEvent.ACTION_UP) :
                        Log.e(DEBUG_TAG,"Action was UP");
                        return true;
                    case (MotionEvent.ACTION_CANCEL) :
                        Log.e(DEBUG_TAG,"Action was CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE) :
                        Log.e(DEBUG_TAG,"Movement occurred outside bounds " +
                                "of current screen element");
                        return true;
                    default :
                        return false;
                }
            }
        });
    }


}