package com.example.typesofadapter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.typesofadapter.SimplActivity;
import com.example.typesofadapter.models.Items;
import com.example.typesofadapter.R;

import java.util.ArrayList;

public class CustomBaseAdapter  extends BaseAdapter {
    ArrayList<Items> arrayList;
    Context context;

    public CustomBaseAdapter(ArrayList<Items> arrayList, Context context) {
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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.baselayotu,null);
        TextView textView = convertView.findViewById(R.id.textview_row);
        ImageView imageView = convertView.findViewById(R.id.imageview_row);
        Button button =convertView.findViewById(R.id.button);
        Items items = arrayList.get(position);
        textView.setText(items.getName());
        imageView.setImageResource(items.getImage());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "sum is "+sum(position,position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SimplActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private int sum(int a,int b)
    {
        return a+b;
    }


}
