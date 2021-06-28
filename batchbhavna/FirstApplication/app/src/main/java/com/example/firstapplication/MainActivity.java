package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapplication.fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
Button sumbitbtn,movetotbtn;
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " );
        //Connecting xm with java attributes
        sumbitbtn = findViewById(R.id.sumbitnbtn);
        movetotbtn = findViewById(R.id.MOVETTOconsteating);
        editText = findViewById(R.id.useremail);
        movetotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = editText.getText().toString().trim();



                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                intent.putExtra("email",emailtext);
//                intent.putExtra("number",456756);
                startActivity(intent);
            }
        });


        sumbitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //value from email edittext
                String email_text = editText.getText().toString().trim();

                Toast.makeText(getApplicationContext(),"email =>"+email_text,Toast.LENGTH_SHORT).show();

            //navigation to second screen
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });

        //fragmentadditon

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentcontainer,new FirstFragment());
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.firstitem)
        {
            Toast.makeText(this, "onclick: " +item.getTitle(), Toast.LENGTH_SHORT).show();

        }else if(id == R.id.seconitem)
        {

        }else if(id == R.id.submentone)
        {
            Toast.makeText(this, "onclick"+item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id == R.id.submenttwo)
        {
            Toast.makeText(this, "onclick" +item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}