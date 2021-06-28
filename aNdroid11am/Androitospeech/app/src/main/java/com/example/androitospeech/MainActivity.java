package com.example.androitospeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    EditText textedit;
    Button convertybtn,movebtn;

    TextToSpeech toSpeech;
    String[] perms = {Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO};
    int permissionconstan = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
toSpeech = new TextToSpeech(this,this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(perms,permissionconstan);
            }
        }
        bindview();
        convertybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sppech_text = textedit.getText().toString().trim();
                speakoutmethod(sppech_text);
            }
        });
        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SppechToText.class));
            }
        });
    }


    private void speakoutmethod(String sppech_text) {
        toSpeech.speak(sppech_text,TextToSpeech.QUEUE_FLUSH,null);

    }

    private void bindview() {
        textedit = findViewById(R.id.speech_Text_edit);
        convertybtn = findViewById(R.id.covert_btn);
       movebtn = findViewById(R.id.speechtotextmove);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS)
        {
            int result = toSpeech.setLanguage(Locale.UK);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Toast.makeText(this, "Language is not supported", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "Intialisation failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        if(toSpeech != null)
        {
            toSpeech.stop();
            toSpeech.shutdown();

        }
        super.onDestroy();
    }
}