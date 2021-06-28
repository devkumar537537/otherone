package com.example.getureexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity  implements  GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
    private static final String TAG = "Getures";
    private GestureDetectorCompat mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " );

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);


    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.e(TAG, "onSingleTapConfirmed: " );
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.e(TAG, "onDoubleTap: " );
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.e(TAG, "onDoubleTapEvent: " );
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG, "onDown: " );
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e(TAG, "onShowPress: " );
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        startActivity(new Intent(MainActivity.this,SecondActivit.class));
        Log.e(TAG, "onSingleTapUp: " );
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Log.e(TAG, "onScroll: " );
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e(TAG, "onLongPress: " );
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(TAG, "onFling: " + e1.toString() + e2.toString());
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}