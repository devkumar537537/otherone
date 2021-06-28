package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    String[] scientist = {"Newton","Albert","Merry Query","Elon Mask","Villgets","xys","Newton","Albert","Merry Query","Elon Mask","Villgets","xys"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,scientist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(SecondActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popmenu,popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.pospupone)
                        {
                            Toast.makeText(SecondActivity.this, "clciked "+item.getTitle(), Toast.LENGTH_SHORT).show();
                        }else if(id == R.id.popseconditem)
                        {
                            Toast.makeText(SecondActivity.this, "clicked "+item.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });


                popupMenu.show();
            }
        });
        registerForContextMenu(listView);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmeny,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.contextfirst)
        {
           int result = sum(23,56);
            Toast.makeText(this, "sum is => "+result, Toast.LENGTH_SHORT).show();
        }else if(id == R.id.ContextSecond){
            int result = multi(23,56);
            Toast.makeText(this, "multi is =>"+result, Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    private int sum(int a ,int b){
        int sum = a+b;
     return sum;
    }

    private int multi(int a,int b)
    {
        int multi = a*b;
        return multi;
    }
}