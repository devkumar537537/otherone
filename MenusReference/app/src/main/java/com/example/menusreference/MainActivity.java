package com.example.menusreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ListView listView ;
Button button;

String[] namelist={"Divyam","Vikash","Aksay","Dev","Android","Divyam","Vikash","Aksay","Dev","Android"};
    String[] numberlist={"9999999","567567","Aksay","Dev","Android","Divyam","Vikash","Aksay","Dev","Android"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        button = findViewById(R.id.popmenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(v.getContext(),button);
                popupMenu.getMenuInflater().inflate(R.menu.context_menuc,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.firstContext)
                        {
                            int sumresult = sum(34,56);
                            System.out.println(sumresult);
                        }else if(item.getItemId() == R.id.secondcontext)
                        {
                            int multiresult = multi(34,56);
                            System.out.println(multiresult);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, namelist);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this,"clicked "+namelist[position],Toast.LENGTH_SHORT).show();
    }
});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.firstoption)
        {
            Toast.makeText(MainActivity.this,"clicked "+item.getTitle(),Toast.LENGTH_SHORT).show();
        }else if(id == R.id.secondotpion)
        {
            Toast.makeText(MainActivity.this,"clicked"+item.getTitle(),Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menuc,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.firstContext)
        {
            Toast.makeText(MainActivity.this,"clicked"+item.getTitle(),Toast.LENGTH_LONG).show();
        }else if(id == R.id.secondcontext)
        {
            Toast.makeText(MainActivity.this,"clicked"+item.getTitle(),Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
    private  int sum(int a ,int b)
    {
        return a+b;
    }

    private int multi(int a,int b)
    {
        return a*b;
    }
}