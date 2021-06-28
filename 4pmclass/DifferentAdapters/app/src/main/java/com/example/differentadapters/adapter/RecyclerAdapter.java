package com.example.differentadapters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.differentadapters.R;
import com.example.differentadapters.models.ModelItem;

import java.util.ArrayList;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHOlder> {
ArrayList<ModelItem> userlist;
Context context;

    public RecyclerAdapter(ArrayList<ModelItem> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @Override
    public RecyclerAdapter.MyViewHOlder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerformat,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder( RecyclerAdapter.MyViewHOlder holder, int position) {
            ModelItem modelItem = userlist.get(position);
            holder.emailview.setText(modelItem.getUsernumber());
            holder.imageView.setImageResource(modelItem.getImageurl());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView emailview;

        public MyViewHOlder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyclerimagae);
            emailview = itemView.findViewById(R.id.useremail);
        }
    }
}
