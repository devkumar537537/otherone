package com.example.typesofadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapter.adapters.CustomArrayAdapter;
import com.example.typesofadapter.models.Items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
ArrayList<Items> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listviearray);
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

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(),R.layout.listview_row_formate,userlist);
        listView.setAdapter(customArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,BaseAdapterexampl.class));
                Toast.makeText(MainActivity.this, "clicked"+userlist.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}