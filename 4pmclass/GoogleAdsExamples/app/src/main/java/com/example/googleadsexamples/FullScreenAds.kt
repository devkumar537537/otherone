package com.example.googleadsexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class FullScreenAds : AppCompatActivity() {
    lateinit var mIterstitialad:InterstitialAd
    lateinit var textView: TextView
    lateinit var progressBar: ProgressBar
    private  val TAG = "FullScreenAds"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_ads)
        textView =findViewById(R.id.adloading)
        progressBar =findViewById(R.id.progressbar);
        progressBar.visibility= View.VISIBLE
        textView.visibility = View.VISIBLE
        MobileAds.initialize(this)
        {
            Toast.makeText(this@FullScreenAds,"Ads skd intilaised",Toast.LENGTH_SHORT).show()
        }
        val adRequest = AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.message)
            progressBar.visibility=View.GONE
                textView.text="Some Erroro Occured"
                Toast.makeText(this@FullScreenAds,"eroro",Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded")
                progressBar.visibility = View.GONE
                Toast.makeText(this@FullScreenAds,"Ad is ready to show",Toast.LENGTH_SHORT).show()
                mIterstitialad= interstitialAd
                if(mIterstitialad != null)
                {
                    mIterstitialad.show(this@FullScreenAds)
                    mIterstitialad?.fullScreenContentCallback = object: FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            Log.d(TAG, "Ad was dismissed.")
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                            Log.d(TAG, "Ad failed to show.")
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d(TAG, "Ad showed fullscreen content")

                        }
                }


            }
        }
        });
    }
}