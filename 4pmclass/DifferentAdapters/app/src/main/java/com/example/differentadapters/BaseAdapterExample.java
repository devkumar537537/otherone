package com.example.differentadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAssignedNumbers;
import android.os.Bundle;
import android.widget.ListView;

import com.example.differentadapters.adapter.CustomBaseAdapter;
import com.example.differentadapters.models.BaseModel;

import java.util.ArrayList;

public class BaseAdapterExample extends AppCompatActivity {
ListView listView;
ArrayList<BaseModel> studentlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        listView = findViewById(R.id.listvaeiwbase);
        studentlist = new ArrayList<>();

        studentlist.add(new BaseModel("234234",R.drawable.arebic));
        studentlist.add(new BaseModel("546564",R.drawable.weather));
        studentlist.add(new BaseModel("67786",R.drawable.cbitss));
        studentlist.add(new BaseModel("959675",R.drawable.download));
        studentlist.add(new BaseModel("76785464",R.drawable.sample));
        studentlist.add(new BaseModel("234234",R.drawable.arebic));
        studentlist.add(new BaseModel("546564",R.drawable.weather));
        studentlist.add(new BaseModel("67786",R.drawable.cbitss));
        studentlist.add(new BaseModel("959675",R.drawable.download));
        studentlist.add(new BaseModel("76785464",R.drawable.sample));
        studentlist.add(new BaseModel("234234",R.drawable.arebic));
        studentlist.add(new BaseModel("546564",R.drawable.weather));
        studentlist.add(new BaseModel("67786",R.drawable.cbitss));
        studentlist.add(new BaseModel("959675",R.drawable.download));
        studentlist.add(new BaseModel("76785464",R.drawable.sample));

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(studentlist,getApplicationContext());
        listView.setAdapter(customBaseAdapter);
    }
}