package com.example.typesofadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.typesofadapters.Recycler.Item;

import java.util.ArrayList;

public class CustomBaseAdapter  extends BaseAdapter {

    ArrayList<Item> arraylist;
    Context context;

    public CustomBaseAdapter(ArrayList<Item> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.listview_row_formate,null);
        ImageView imageView = convertView.findViewById(R.id.imageview_row);
        TextView textView = convertView.findViewById(R.id.textview_row);

        imageView.setImageResource(arraylist.get(position).getImageurl());
        textView.setText(arraylist.get(position).getName());
        return convertView;
    }
}
