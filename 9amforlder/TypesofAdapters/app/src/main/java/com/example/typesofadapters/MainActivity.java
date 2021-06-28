package com.example.typesofadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapters.Recycler.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
ArrayList<Item> itemdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview_arrayadatper);

        itemdata = new ArrayList<>();

        itemdata.add(new Item(R.drawable.arebic, "First"));
        itemdata.add(new Item(R.drawable.background, "Second"));
        itemdata.add(new Item(R.drawable.first, "Third"));
        itemdata.add(new Item(R.drawable.naturetwo, "Fourth"));
        itemdata.add(new Item(R.drawable.arebic, "First"));
        itemdata.add(new Item(R.drawable.background, "Second"));
        itemdata.add(new Item(R.drawable.first, "Third"));
        itemdata.add(new Item(R.drawable.naturetwo, "Fourth"));
        itemdata.add(new Item(R.drawable.arebic, "First"));
        itemdata.add(new Item(R.drawable.background, "Second"));
        itemdata.add(new Item(R.drawable.first, "Third"));
        itemdata.add(new Item(R.drawable.naturetwo, "Fourth"));

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(), R.layout.listview_row_formate, itemdata);

        listView.setAdapter(customArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "clicked "+itemdata.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,BaseAdapterExample.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}