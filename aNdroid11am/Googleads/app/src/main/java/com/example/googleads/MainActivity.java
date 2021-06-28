package com.example.googleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
Button buttton,btttonvideo;

    private AdView madview;
    private static final String TAG = "BannerAddss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madview = findViewById(R.id.adView);
buttton = findViewById(R.id.movetointerisalads);
btttonvideo = findViewById(R.id.movetovideoads);

    btttonvideo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,VideoAds.class));
    }
   });
    buttton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,IntererstialAds.class));
    }
    });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MainActivity.this, "Intialisation complete", Toast.LENGTH_SHORT).show();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);
        madview.setAdListener(new AdListener(){
            @Override
            public void onAdClicked() {
                Log.e(TAG, "onAdClicked: " );
            }

            @Override
            public void onAdClosed() {
                Log.e(TAG, "onAdClosed: " );
            }

            @Override
            public void onAdOpened() {
                Log.e(TAG, "onAdOpened: ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e(TAG, "onAdFailedToLoad: " );
            }

            @Override
            public void onAdImpression() {
                Log.e(TAG, "onAdImpression: " );
            }

            @Override
            public void onAdLoaded() {
                Log.e(TAG, "onAdLoaded: " );
            }
        });
    }
}