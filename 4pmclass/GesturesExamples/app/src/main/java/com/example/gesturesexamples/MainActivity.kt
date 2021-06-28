package com.example.gesturesexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener  {
    private  val TAG = "MainActivity"

    private var mDetector: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDetector = GestureDetectorCompat(this, this)
        mDetector!!.setOnDoubleTapListener(this)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (mDetector!!.onTouchEvent(event)) {
            true
        } else super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDown: " )
       return true
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.e(TAG, "onShowPress: " )
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapUp: " )
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.e(TAG, "onScroll: " )
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.e(TAG, "onLongPress: " )
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.e(TAG, "onFling: ")
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapConfirmed: " )
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        startActivity(Intent(applicationContext,TelephonyManager::class.java))
        Log.e(TAG, "onDoubleTap: " )
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDoubleTapEvent: " )
        return true
    }
}