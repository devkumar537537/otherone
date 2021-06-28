package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceexample.models.Root;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button playvideobtn;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    TextView textView;
    ApiServices apiServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView = findViewById(R.id.youtubeplayoutview);
        playvideobtn = findViewById(R.id.playbtn);
        textView = findViewById(R.id.textiview);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
           youTubePlayer.loadVideo("0jjFjC30-4A");
                Toast.makeText(MainActivity.this, "intialisation complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MainActivity.this, "error "+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        playvideobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youTubePlayerView.initialize("AIzaSyCasWsMa9W-ayAPADVMmzISsnJKlvjl4pk",onInitializedListener);
            }
        });
        getresponser();
    }

    private void getresponser() {

        apiServices = ApiClient.getRerorfit().create(ApiServices.class);
       Call<Root> resonsecall =  apiServices.getresponseyoutube("snippet","UCMtFAi84ehTSYSE9XoHefig","AIzaSyCasWsMa9W-ayAPADVMmzISsnJKlvjl4pk");
    resonsecall.enqueue(new Callback<Root>() {
        @Override
        public void onResponse(Call<Root> call, Response<Root> response) {
            Root root = response.body();
            textView.setText(root.getEtag());
        }

        @Override
        public void onFailure(Call<Root> call, Throwable t) {
            Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

    }
}