package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button;
EditText nameditt;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.generatetoast);
       nameditt = findViewById(R.id.namedit);
       imageView = findViewById(R.id.imageview);


       int id = R.drawable.naturetwo;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LayoutInflater infalter = getLayoutInflater();

              View customlayout = infalter.inflate(R.layout.customlayout,(ViewGroup) findViewById(R.id.customlayout));
                TextView textView = customlayout.findViewById(R.id.textview);
                textView.setText("it just testing");
                Toast toast = new Toast(MainActivity.this);
                toast.setView(customlayout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL,100,150);
                toast.show();
               String nametxt = nameditt.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("name",nametxt);
                intent.putExtra("imageid",id);
                startActivity(intent);
            }
        });
    }
}