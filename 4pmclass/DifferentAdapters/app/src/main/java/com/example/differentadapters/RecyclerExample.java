package com.example.differentadapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.differentadapters.adapter.RecyclerAdapter;
import com.example.differentadapters.models.ModelItem;

import java.util.ArrayList;

public class RecyclerExample extends AppCompatActivity {
RecyclerView recyclerView;
    ArrayList<ModelItem> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_example);
        recyclerView = findViewById(R.id.recylerviewlist);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        userlist = new ArrayList<>();


        userlist.add(new ModelItem(R.drawable.arebic,"1234"));
        userlist.add(new ModelItem(R.drawable.background,"1235"));
        userlist.add(new ModelItem(R.drawable.cbitss,"1239"));
        userlist.add(new ModelItem(R.drawable.naturetwo,"23456"));
        userlist.add(new ModelItem(R.drawable.download,"1209808"));
        userlist.add(new ModelItem(R.drawable.saveimages,"98797"));
        userlist.add(new ModelItem(R.drawable.arebic,"1234"));
        userlist.add(new ModelItem(R.drawable.background,"1235"));
        userlist.add(new ModelItem(R.drawable.cbitss,"1239"));
        userlist.add(new ModelItem(R.drawable.naturetwo,"23456"));
        userlist.add(new ModelItem(R.drawable.download,"1209808"));
        userlist.add(new ModelItem(R.drawable.saveimages,"98797"));
        userlist.add(new ModelItem(R.drawable.arebic,"1234"));
        userlist.add(new ModelItem(R.drawable.background,"1235"));
        userlist.add(new ModelItem(R.drawable.cbitss,"1239"));
        userlist.add(new ModelItem(R.drawable.naturetwo,"23456"));
        userlist.add(new ModelItem(R.drawable.download,"1209808"));
        userlist.add(new ModelItem(R.drawable.saveimages,"98797"));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(userlist,getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
    }
}