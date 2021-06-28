package com.example.typesofadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.typesofadapters.Recycler.Item;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> itemdata;
    Context context;
    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<Item> itemdata) {
        super(context, resource);
        this.context = context;
        this.itemdata = itemdata;
    }

    @Override
    public int getCount() {
        return itemdata.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View v = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     v = layoutInflater.inflate(R.layout.listview_row_formate,null);

        ImageView imageView = v.findViewById(R.id.imageview_row);
        TextView textView = v.findViewById(R.id.textview_row);

        imageView.setImageResource(itemdata.get(position).getImageurl());
        textView.setText(itemdata.get(position).getName());
        return v;

    }
}
