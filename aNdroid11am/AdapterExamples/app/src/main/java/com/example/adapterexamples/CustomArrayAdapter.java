package com.example.adapterexamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<ModelItem> {

    ArrayList<ModelItem> arrayList;
    Context context;
    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<ModelItem> arrayList) {
        super(context, resource);
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       view = layoutInflater.inflate(R.layout.array_format,null);
        ImageView imageView = view.findViewById(R.id.format_imageview);
        TextView username = view.findViewById(R.id.format_username);

        imageView.setImageResource(arrayList.get(position).getImageurl());
        username.setText(arrayList.get(position).getName());

        return view;
    }
}
