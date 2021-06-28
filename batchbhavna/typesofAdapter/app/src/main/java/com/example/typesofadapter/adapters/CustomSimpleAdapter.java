package com.example.typesofadapter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.typesofadapter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSimpleAdapter extends SimpleAdapter {
   ArrayList<HashMap<String,String>> userlist;
   Context context;
    public CustomSimpleAdapter(Context context,ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        userlist = data;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  super.getView(position, convertView, parent);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(position);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    private void show(int a)
    {
        switch (a)
        {
            case 1:
                Toast.makeText(context, "January", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(context, "March", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(context, "April", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(context, "December", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "This is not a case", Toast.LENGTH_SHORT).show();

        }
    }
}
