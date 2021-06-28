package com.example.typesofadapter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.typesofadapter.models.Items;
import com.example.typesofadapter.R;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Items> {
    ArrayList<Items> userlist;
    Context context;
    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<Items> userlist) {
        super(context, resource);
        this.context = context;
        this.userlist = userlist;

    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_row_formate,null);
        TextView textView = view.findViewById(R.id.textview_row);
        ImageView imageView = view.findViewById(R.id.imageview_row);
        Items items = userlist.get(position);
        textView.setText(items.getName());
        imageView.setImageResource(items.getImage());
        return view;
    }
}
