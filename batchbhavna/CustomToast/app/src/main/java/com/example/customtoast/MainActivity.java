package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button producetoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        producetoast = findViewById(R.id.button);
        producetoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.customtoast,null);
                ImageView imageView = view.findViewById(R.id.imageviewtoast);
                TextView textView = view.findViewById(R.id.customtaosttextview);

                textView.setText("Custom Toast");
                imageView.setImageResource(R.drawable.ic_launcher_background);
                Toast toast = new Toast(getApplicationContext());
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL,100,200);
                toast.show();


            }
        });
    }
}