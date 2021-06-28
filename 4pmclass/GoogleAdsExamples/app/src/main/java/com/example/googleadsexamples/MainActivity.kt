package com.example.googleadsexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
lateinit var adView: AdView
    lateinit var movetovideo:Button
    lateinit var movetofullscren:Button
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adView = findViewById(R.id.adView)
        movetovideo = findViewById(R.id.movetoVideo)
        movetofullscren=findViewById(R.id.movetofullscre)
        movetofullscren.setOnClickListener {
            startActivity(Intent(this@MainActivity,FullScreenAds::class.java));

        }
        movetovideo.setOnClickListener {
            startActivity(Intent(this@MainActivity,VideoAds::class.java));

        }
        MobileAds.initialize(this){
            Toast.makeText(this@MainActivity,"Ads skd intilaised",Toast.LENGTH_SHORT).show()
        }

        val adReguest = AdRequest.Builder().build()
        adView.loadAd(adReguest)
adView.adListener = object :AdListener(){
    override fun onAdClosed() {
        Log.e(TAG, "onAdClosed: " )
    }
    override fun onAdLoaded() {
        Log.e(TAG, "onAdLoaded: " )
    }
}


    }
}