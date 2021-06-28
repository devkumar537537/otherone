package com.example.typesofadapters.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.typesofadapters.R;
import com.example.typesofadapters.adapters.RecylerAdapter;
import com.example.typesofadapters.models.Recyclermodel;

import java.util.ArrayList;


public class RecyclerViewExample extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Recyclermodel> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        recyclerView = findViewById(R.id.recyclerview);


        userlist = new ArrayList<>();
        userlist.add(new Recyclermodel(R.drawable.arebic));
        userlist.add(new Recyclermodel(R.drawable.naturetwo));
        userlist.add(new Recyclermodel(R.drawable.first));
        userlist.add(new Recyclermodel(R.drawable.download));
        userlist.add(new Recyclermodel(R.drawable.weather));
        userlist.add(new Recyclermodel(R.drawable.third));
        userlist.add(new Recyclermodel(R.drawable.arebic));
        userlist.add(new Recyclermodel(R.drawable.naturetwo));
        userlist.add(new Recyclermodel(R.drawable.first));
        userlist.add(new Recyclermodel(R.drawable.download));
        userlist.add(new Recyclermodel(R.drawable.weather));
        userlist.add(new Recyclermodel(R.drawable.third));
        userlist.add(new Recyclermodel(R.drawable.arebic));
        userlist.add(new Recyclermodel(R.drawable.naturetwo));
        userlist.add(new Recyclermodel(R.drawable.first));
        userlist.add(new Recyclermodel(R.drawable.download));
        userlist.add(new Recyclermodel(R.drawable.weather));
        userlist.add(new Recyclermodel(R.drawable.third));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        RecylerAdapter recylerAdapter = new RecylerAdapter(userlist,getApplicationContext());
        recyclerView.setAdapter(recylerAdapter);

    }
}