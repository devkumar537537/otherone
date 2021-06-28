package com.example.typesofadapters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.typesofadapters.R;
import com.example.typesofadapters.adapters.CustomSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomSimpleAdaptertExample extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String,String>> hashMapArrayList;
    int[] imageid = {R.drawable.arebic,R.drawable.download,R.drawable.first,R.drawable.naturetwo,R.drawable.arebic,R.drawable.naturetwo,R.drawable.first,R.drawable.naturetwo,R.drawable.first,R.drawable.naturetwo};
    String[] namelist = {"First","Second","Third","Fourth","First","Second","Third","Fourth","Third","Fourth"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_simple_adaptert_example);
        listView = findViewById(R.id.listveiwsimpel);
        hashMapArrayList = new ArrayList<>();
        for(int i=0;i<=imageid.length-1;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",imageid[i]+"");
            hashMap.put("name",namelist[i]);

            hashMapArrayList.add(hashMap);
        }


        String[] from = {"image","name"};
        int[] to = {R.id.imageviewbase,R.id.textviewbase};

        CustomSimpleAdapter customSimpleAdapter = new CustomSimpleAdapter(getApplicationContext(),hashMapArrayList,R.layout.newbaseformate,from,to);

        listView.setAdapter(customSimpleAdapter);

    }
}