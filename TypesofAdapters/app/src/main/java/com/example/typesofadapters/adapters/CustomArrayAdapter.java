package com.example.typesofadapters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.typesofadapters.R;
import com.example.typesofadapters.models.Item;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> arrayList;
    Context context;


    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<Item> arrayList) {
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

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.listview_row_formate,null);
        ImageView imageView = view.findViewById(R.id.imageview_row);
        TextView textView = view.findViewById(R.id.textview_row);

       Item ione = arrayList.get(position);

       textView.setText(ione.getName());
       imageView.setImageResource(ione.getImageurl());

        return view;
    }


}
