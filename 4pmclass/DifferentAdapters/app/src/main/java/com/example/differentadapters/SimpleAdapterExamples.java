package com.example.differentadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.differentadapters.adapter.CustomSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterExamples extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> userlist;
    String[] numberst = {"342343","7978978","7655464","234342","6575675","2134234","453534"};
    int[] imagelist = {R.drawable.arebic,R.drawable.background,R.drawable.cbitss,R.drawable.download,R.drawable.naturetwo,R.drawable.right,R.drawable.sample};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter_examples);
        listView = findViewById(R.id.simplelistview);
        userlist = new ArrayList<>();

        for(int i = 0;i<numberst.length;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",imagelist[i]+"");
            hashMap.put("number",numberst[i]);

            userlist.add(hashMap);
        }
        String[] from = {"image","number"};
        int[] to = {R.id.imageview,R.id.usernumber};

        CustomSimpleAdapter customSimpleAdapter = new CustomSimpleAdapter(getApplicationContext(),userlist,R.layout.listitem,from,to);

        listView.setAdapter(customSimpleAdapter);

    }
}