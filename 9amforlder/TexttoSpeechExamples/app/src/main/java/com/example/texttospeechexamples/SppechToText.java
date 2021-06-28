package com.example.texttospeechexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;

public class SppechToText extends AppCompatActivity {
    static  final Integer RecordAudoRequest = 1;
    private SpeechRecognizer speechRecognizer;
    private EditText editText;
    private ImageView imageView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sppech_to_text);
        bindview();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecoginserintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecoginserintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecoginserintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                imageView.setImageResource(R.drawable.mic);

                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setHint(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    speechRecognizer.stopListening();
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    imageView.setImageResource(R.drawable.mic2);
                    speechRecognizer.startListening(speechRecoginserintent);
                }
                return false;
            }
        });
    }

    private void bindview() {
        editText = findViewById(R.id.convert_to_speectedit);
        imageView = findViewById(R.id.mic_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }
}