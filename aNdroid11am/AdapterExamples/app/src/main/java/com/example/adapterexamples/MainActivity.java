package com.example.adapterexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
ArrayList<ModelItem> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.arraylistview);
        arrayList = new ArrayList<>();


        arrayList.add(new ModelItem("First",R.drawable.arebic));
        arrayList.add(new ModelItem("Second",R.drawable.background));
        arrayList.add(new ModelItem("Third",R.drawable.cbitss));
        arrayList.add(new ModelItem("First",R.drawable.arebic));
        arrayList.add(new ModelItem("Second",R.drawable.background));
        arrayList.add(new ModelItem("Third",R.drawable.cbitss));
        arrayList.add(new ModelItem("First",R.drawable.arebic));
        arrayList.add(new ModelItem("Second",R.drawable.background));
        arrayList.add(new ModelItem("Third",R.drawable.cbitss));
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(),R.layout.array_format,arrayList);

        listView.setAdapter(customArrayAdapter);
    }
}