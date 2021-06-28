package com.example.typesofadapters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.typesofadapters.R;
import com.example.typesofadapters.adapters.CustomBaseAdapter;
import com.example.typesofadapters.models.Itemnumber;

import java.util.ArrayList;

public class BaseAdapterexame extends AppCompatActivity {
ListView listView;
ArrayList<Itemnumber> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapterexame);
        listView = findViewById(R.id.listviewbase);
        arrayList = new ArrayList<>();




        arrayList.add(new Itemnumber(R.drawable.arebic,"234234234"));
        arrayList.add(new Itemnumber(R.drawable.download,"265756"));
        arrayList.add(new Itemnumber(R.drawable.first,"2378678234"));
        arrayList.add(new Itemnumber(R.drawable.naturetwo,"234237867834"));
        arrayList.add(new Itemnumber(R.drawable.right,"234234234"));
        arrayList.add(new Itemnumber(R.drawable.third,"234234234"));
        arrayList.add(new Itemnumber(R.drawable.arebic,"234234234"));
        arrayList.add(new Itemnumber(R.drawable.download,"265756"));
        arrayList.add(new Itemnumber(R.drawable.first,"2378678234"));
        arrayList.add(new Itemnumber(R.drawable.naturetwo,"234237867834"));
        arrayList.add(new Itemnumber(R.drawable.right,"234234234"));
        arrayList.add(new Itemnumber(R.drawable.third,"234234234"));

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(arrayList,getApplicationContext());
        listView.setAdapter(customBaseAdapter);

    }
}