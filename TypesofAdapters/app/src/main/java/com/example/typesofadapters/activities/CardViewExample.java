package com.example.typesofadapters.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.typesofadapters.R;

public class CardViewExample extends AppCompatActivity {
CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_example);
        cardView = findViewById(R.id.cardviewbtn);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardViewExample.this, "This is sample click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}