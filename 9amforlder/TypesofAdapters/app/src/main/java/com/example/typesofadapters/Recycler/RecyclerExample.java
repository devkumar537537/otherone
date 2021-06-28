package com.example.typesofadapters.Recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.typesofadapters.R;

import java.util.ArrayList;

public class RecyclerExample extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Item> itemdata ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_example);
        recyclerView = findViewById(R.id.recycerview);
        itemdata  = new ArrayList<>();


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

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,7);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(itemdata,getApplicationContext());

        recyclerView.setAdapter(recyclerAdapter);
    }
}