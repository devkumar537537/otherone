package com.example.typesofadapters.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSimpleAdapter  extends SimpleAdapter {
    ArrayList<HashMap<String,String>> data;
    Context context;
    public CustomSimpleAdapter(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data = data;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  super.getView(position, convertView, parent);
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
