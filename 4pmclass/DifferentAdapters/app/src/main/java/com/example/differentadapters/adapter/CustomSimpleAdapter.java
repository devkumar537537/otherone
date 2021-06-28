package com.example.differentadapters.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSimpleAdapter extends SimpleAdapter {
ArrayList<HashMap<String,String>> userlist;
Context context;
    public CustomSimpleAdapter(Context context, ArrayList<HashMap<String,String>> userlist, int resource, String[] from, int[] to) {
        super(context, userlist, resource, from, to);
        this.userlist = userlist;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vew =  super.getView(position, convertView, parent);
        
        return vew;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }
}
