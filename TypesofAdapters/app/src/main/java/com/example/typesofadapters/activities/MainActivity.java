package com.example.typesofadapters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapters.R;
import com.example.typesofadapters.adapters.CustomArrayAdapter;
import com.example.typesofadapters.models.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
    private static final String TAG = "MainActivity";

ArrayList<Item> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
userlist = new ArrayList<>();
        listView = findViewById(R.id.listview);
userlist.add(new Item(R.drawable.arebic,"First"));
userlist.add(new Item(R.drawable.download,"Second"));
userlist.add(new Item(R.drawable.first,"Third"));
userlist.add(new Item(R.drawable.naturetwo,"Fourt"));
userlist.add(new Item(R.drawable.right,"fiftth"));
userlist.add(new Item(R.drawable.sample,"Sixth"));
        userlist.add(new Item(R.drawable.arebic,"First"));
        userlist.add(new Item(R.drawable.download,"Second"));
        userlist.add(new Item(R.drawable.first,"Third"));
        userlist.add(new Item(R.drawable.naturetwo,"Fourt"));
        userlist.add(new Item(R.drawable.right,"fiftth"));
        userlist.add(new Item(R.drawable.sample,"Sixth"));

      CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(),R.layout.listview_row_formate,userlist);
listView.setAdapter(customArrayAdapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = userlist.get(position);



        Toast.makeText(MainActivity.this, "name => "+item.getName(), Toast.LENGTH_SHORT).show();
   startActivity(new Intent(MainActivity.this, BaseAdapterexame.class));

    }
});

    }
}