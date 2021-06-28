package com.example.recycleradapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewholder> {
    ArrayList<RecyclerModel> arrayList;
    Context context;

    public RecyclerAdapter(ArrayList<RecyclerModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclre_format,parent,false);

        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewholder holder, int position) {
                   RecyclerModel recyclerModel = arrayList.get(position);
                  holder.imageView.setImageResource(recyclerModel.getImagurl());
                  holder.username.setText(recyclerModel.getName());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,SecondActivty.class);
        intent.putExtra("name",recyclerModel.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView username;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyer_image);
            username = itemView.findViewById(R.id.recycler_name);
        }
    }
}
