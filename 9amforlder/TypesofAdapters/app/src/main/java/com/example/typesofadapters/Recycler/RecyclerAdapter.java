package com.example.typesofadapters.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.typesofadapters.NextActiivty;
import com.example.typesofadapters.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyVieHolder> {
    ArrayList<Item> arrayList;
    Context context;

    public RecyclerAdapter(ArrayList<Item> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);

        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyVieHolder holder, int position) {


        Item itemdata = arrayList.get(position);

        holder.imageView.setImageResource(itemdata.getImageurl());
        holder.textView.setText(itemdata.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, NextActiivty.class);
              intent.putExtra("userid",itemdata.getName());
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyVieHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public MyVieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview_row_recycler);
            textView = itemView.findViewById(R.id.textview_row_recycler);
        }
    }
}
