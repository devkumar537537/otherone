package com.example.recycleradapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<RecyclerModel> userlist;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userlist = new ArrayList<>();
        bindview();

      userlist.add(new RecyclerModel("First",R.drawable.background));
      userlist.add(new RecyclerModel("Second",R.drawable.sample));
        userlist.add(new RecyclerModel("First",R.drawable.background));
        userlist.add(new RecyclerModel("Second",R.drawable.sample));
        userlist.add(new RecyclerModel("First",R.drawable.background));
        userlist.add(new RecyclerModel("Second",R.drawable.sample));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager( layoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(userlist,getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void bindview() {
        recyclerView = findViewById(R.id.recyview);
        toolbar = findViewById(R.id.toolbar);

    }
}