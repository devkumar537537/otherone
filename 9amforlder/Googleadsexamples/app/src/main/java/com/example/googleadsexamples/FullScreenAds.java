package com.example.googleadsexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class FullScreenAds extends AppCompatActivity {
    private InterstitialAd minterstitiadads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_ads);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().build();
        minterstitiadads = new InterstitialAd(getApplicationContext());

        minterstitiadads.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        minterstitiadads.loadAd(adRequest);
        minterstitiadads.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                displayoutadd();
            }
        });
    }
    private void displayoutadd() {
        if(minterstitiadads.isLoaded())
        {
            minterstitiadads.show();
        }
    }
}