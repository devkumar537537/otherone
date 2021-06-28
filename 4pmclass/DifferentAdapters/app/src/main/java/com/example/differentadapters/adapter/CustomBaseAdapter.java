package com.example.differentadapters.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.differentadapters.R;
import com.example.differentadapters.models.BaseModel;
import com.example.differentadapters.models.ModelItem;

import java.util.ArrayList;
import java.util.Random;

public class CustomBaseAdapter extends BaseAdapter {
ArrayList<BaseModel> studentlist;
Context context;

    public CustomBaseAdapter(ArrayList<BaseModel> studentlist, Context context) {
        this.studentlist = studentlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentlist.size();
    }

    @Override
    public Object getItem(int position) {
        return studentlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.baseformate,parent,false);


        ImageView imageView = view.findViewById(R.id.baseimageview);
        TextView textView = view.findViewById(R.id.basestudentumber);
        Button colorbtn = view.findViewById(R.id.colorbtn);
        RelativeLayout relativeLayout = view.findViewById(R.id.relativelayotu);

        BaseModel modelItem = studentlist.get(position);

        imageView.setImageResource(modelItem.getStudentimage());
        textView.setText(modelItem.getStudentnumber());

        colorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int color = Color.argb(255,random.nextInt(255),random.nextInt(255),random.nextInt(255));
            relativeLayout.setBackgroundColor(color);


            }
        });
        return view;
    }
}
