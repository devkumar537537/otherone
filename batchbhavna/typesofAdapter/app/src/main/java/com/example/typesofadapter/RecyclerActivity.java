package com.example.typesofadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.typesofadapter.adapters.RecyclerviewAdapter;
import com.example.typesofadapter.models.Items;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Items> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        userlist = new ArrayList<>();



        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));
        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));
        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));
        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));
        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));
        userlist.add(new Items(R.drawable.arebic,"Android"));
        userlist.add(new Items(R.drawable.sample,"Sample"));
        userlist.add(new Items(R.drawable.right,"Right"));
        userlist.add(new Items(R.drawable.third,"Third"));

        RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(userlist,getApplicationContext());

        recyclerView.setAdapter(recyclerviewAdapter);
    }
}