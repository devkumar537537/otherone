package com.example.typesofadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.typesofadapter.adapters.CustomSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SimplActivity extends AppCompatActivity {
ListView listView;
    int[] imageid = {R.drawable.arebic,R.drawable.background,R.drawable.cbitss,R.drawable.naturetwo,R.drawable.arebic,R.drawable.background,R.drawable.cbitss,R.drawable.naturetwo};
    String[] namelist = {"First","Second","Third","Fourth","First","Second","Third","Fourth"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpl);
        listView = findViewById(R.id.listviewsimple);

        ArrayList<HashMap<String,String>> userlist = new ArrayList<>();

        for(int i =0;i<imageid.length;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",imageid[i]+"");
            hashMap.put("name",namelist[i]);

            userlist.add(hashMap);

        }

        String[] from = {"image","name"};
        int[] to = {R.id.imageview_row,R.id.textview_row};

        CustomSimpleAdapter customSimpleAdapter = new CustomSimpleAdapter(getApplicationContext(),userlist,R.layout.baselayotu,from,to);
        listView.setAdapter(customSimpleAdapter);

    }
}