package com.example.typesofadapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSimpleAdapter  extends SimpleAdapter {
ArrayList<HashMap<String,String>> data;

    public CustomSimpleAdapter(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = view.findViewById(R.id.textview_row);
        return  view;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
