package com.example.googleadsexamples

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class VideoAds : AppCompatActivity() {
lateinit var loadvideobtn:Button
    lateinit var progressbarvideo:ProgressBar
    lateinit var adRequst:AdRequest
    lateinit var mrewardedAd:RewardedAd
    private  val TAG = "VideoAds"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_ads)
        loadvideobtn = findViewById(R.id.showvideobtn)
        progressbarvideo = findViewById(R.id.progressbarvideo)
        progressbarvideo.visibility = View.VISIBLE
        adRequst =AdRequest.Builder().build()
        loadvideobtn.setOnClickListener {
            loadvdeo()
        }
    }

    private fun loadvdeo() {
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917",adRequst, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.message)
                progressbarvideo.visibility = View.GONE

            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                Log.d(TAG, "Ad was loaded.")
                mrewardedAd = rewardedAd
                progressbarvideo.visibility = View.GONE
                Toast.makeText(applicationContext,"show the add now",Toast.LENGTH_SHORT).show()
                if(mrewardedAd !=null)
                {
                    val acitivitcontext :Activity = this@VideoAds
                    mrewardedAd.show(acitivitcontext){rewardItem ->

                        Log.e(TAG, "amount deleered ${rewardItem.amount}  \n type of reward ${rewardItem.type}" )
                    }
                }
            }
        })
    }

}