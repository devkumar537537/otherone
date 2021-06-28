package com.example.typesofadapters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.typesofadapters.R;
import com.example.typesofadapters.models.Itemnumber;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    ArrayList<Itemnumber> arrayList;
    Context context;

    public CustomBaseAdapter(ArrayList<Itemnumber> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater layoutInflater =(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       v = layoutInflater.inflate(R.layout.newbaseformate,null);

        ImageView imageView = v.findViewById(R.id.imageviewbase);
        TextView textView = v.findViewById(R.id.textviewbase);

        Itemnumber ione = arrayList.get(position);
        imageView.setImageResource(ione.getImageurl());
        textView.setText(ione.getNumber());

        return v;
    }
}
