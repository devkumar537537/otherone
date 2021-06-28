package com.example.differentadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.differentadapters.adapter.CustomArrayAdapter;
import com.example.differentadapters.models.ModelItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;

ArrayList<ModelItem> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listviewarray);
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
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(),R.layout.listitem,userlist);

        listView.setAdapter(customArrayAdapter);


    }
}