package com.example.typesofadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class NextActiivty extends AppCompatActivity {
TextView textView;
String value;
Button popupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_actiivty);
        value = getIntent().getStringExtra("userid");
        textView = findViewById(R.id.textView);
        popupbtn = findViewById(R.id.popupmenu_btn);
        textView.setText(value);
        popupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),popupbtn);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popmenufile,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if(id == R.id.popupfirst)
                        {
                            Toast.makeText(NextActiivty.this, "clicke "+item.getTitle(), Toast.LENGTH_SHORT).show();
                        }else if(id == R.id.popupsecond)
                        {
                            Toast.makeText(NextActiivty.this, "click "+item.getTitle(), Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
}