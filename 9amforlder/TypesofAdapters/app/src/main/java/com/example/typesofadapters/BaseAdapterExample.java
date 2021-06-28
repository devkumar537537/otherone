package com.example.typesofadapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapters.Recycler.Item;

import java.util.ArrayList;

public class BaseAdapterExample extends AppCompatActivity {
ListView listView;
ArrayList<Item> itemdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_example);
        listView = findViewById(R.id.base_listview);

        itemdata = new ArrayList<>();

        itemdata.add(new Item(R.drawable.arebic, "Other"));
        itemdata.add(new Item(R.drawable.background, "Another"));
        itemdata.add(new Item(R.drawable.first, "Albert"));
        itemdata.add(new Item(R.drawable.naturetwo, "Newton"));
        itemdata.add(new Item(R.drawable.arebic, "First"));
        itemdata.add(new Item(R.drawable.background, "Second"));
        itemdata.add(new Item(R.drawable.first, "Third"));
        itemdata.add(new Item(R.drawable.naturetwo, "Fourth"));
        itemdata.add(new Item(R.drawable.arebic, "First"));
        itemdata.add(new Item(R.drawable.background, "Second"));
        itemdata.add(new Item(R.drawable.first, "Third"));
        itemdata.add(new Item(R.drawable.naturetwo, "Fourth"));
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(itemdata,getApplicationContext());

        listView.setAdapter(customBaseAdapter);
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.contextfirst){
            Toast.makeText(this, "click "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id == R.id.contextsecond)
        {
            Toast.makeText(this, "click "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}