package com.example.typesofadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.typesofadapter.adapters.CustomBaseAdapter;
import com.example.typesofadapter.adapters.CustomSimpleAdapter;
import com.example.typesofadapter.models.Items;

import java.util.ArrayList;

public class BaseAdapterexampl extends AppCompatActivity {
ListView listView;
ArrayList<Items> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
listView = findViewById(R.id.listbaesadapter);
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

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(userlist,getApplicationContext());
        listView.setAdapter(customBaseAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            startActivity(new Intent(BaseAdapterexampl.this,BaseAdapterexampl.class));
                            Toast.makeText(BaseAdapterexampl.this, "clicked"+userlist.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
            });
    }
}