package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class RelativeExamples : AppCompatActivity() {
    var TAG = "RelativeExample"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_examples)
        Log.e(TAG,"Oncreate method called")
    }
    override fun onStart() {
        super.onStart()
        Log.e(TAG,"OnStart mehtod calleed")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"OnPause mehtod calleed")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"OnResume mehtod calleed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"OnDestroy mehtod calleed")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"OnStope mehtod calleed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG,"OnRestart mehtod calleed")
    }
}