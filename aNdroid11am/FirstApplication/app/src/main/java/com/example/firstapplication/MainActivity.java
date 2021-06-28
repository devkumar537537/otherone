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
import android.widget.Toast;

import com.example.firstapplication.fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {
    Button movetosecond,movetothird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first time fragment additon code in mainActivity

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new FirstFragment());
        fragmentTransaction.commit();


        movetosecond = findViewById(R.id.moveto_second);
        movetothird = findViewById(R.id.move_to_third);
        movetothird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetothird = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(movetothird);
            }
        });

        movetosecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  movetinten = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(movetinten);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.firstitem)
        {
            Toast.makeText(this, "clicked : "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id == R.id.secondItem)
        {
            Toast.makeText(this, "clicked :"+item.getTitle(), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}