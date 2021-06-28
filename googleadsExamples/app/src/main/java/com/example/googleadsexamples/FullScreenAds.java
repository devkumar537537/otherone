package com.example.googleadsexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class FullScreenAds extends AppCompatActivity {


    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_ads);
        progressBar = findViewById(R.id.interestialprogressbar);
        textView = findViewById(R.id.adloading);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        AdRequest adIRequest = new AdRequest.Builder().build();

        mInterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",adIRequest,new InterstitialAdLoadCallback(){

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                Toast.makeText(FullScreenAds.this, "Ad initialsed", Toast.LENGTH_SHORT).show();
                mInterstitialAd = interstitialAd;
                if(mInterstitialAd != null)
                {
                    mInterstitialAd.show(FullScreenAds.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Toast.makeText(FullScreenAds.this, "Ad is closed now", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                progressBar.setVisibility(View.GONE);
                textView.setText("Thanks for watching");

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                progressBar.setVisibility(View.GONE);
                textView.setText("Some Error occured");
                Toast.makeText(FullScreenAds.this, "some error"+loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}