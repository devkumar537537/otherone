package com.example.typesofadapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapters.Recycler.RecyclerAdapter;
import com.example.typesofadapters.Recycler.RecyclerExample;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomSimpleAdapterView extends AppCompatActivity {
ListView listView;
ArrayList<HashMap<String,String>> hashMapArrayList;
int[] imageid = {R.drawable.arebic,R.drawable.background,R.drawable.first,R.drawable.naturetwo,R.drawable.arebic,R.drawable.background,R.drawable.first,R.drawable.naturetwo};
String[] namelist = {"First","Second","Third","Fourth","First","Second","Third","Fourth"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_simple_adapter_view);
        listView = findViewById(R.id.list_simpleview);
        hashMapArrayList = new ArrayList<>();
        for(int i=0;i<=imageid.length-1;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",imageid[i]+"");
            hashMap.put("name",namelist[i]);

            hashMapArrayList.add(hashMap);
        }


        String[] from = {"image","name"};
        int[] to = {R.id.imageview_row,R.id.textview_row};


        CustomSimpleAdapter customSimpleAdapter = new CustomSimpleAdapter(getApplicationContext(),hashMapArrayList,R.layout.listview_row_formate,from,to);
    listView.setAdapter(customSimpleAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CustomSimpleAdapterView.this, RecyclerExample.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.firstitem)
        {
            Toast.makeText(this, "message"+item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id == R.id.Secont_item)
        {
            Toast.makeText(this, "message"+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}