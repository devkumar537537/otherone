package com.example.retorfiteexxample9am;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.MyViewHolder> {
    ArrayList<Comments> commnetList;
    Context context;

    public CommentRecyclerAdapter(ArrayList<Comments> commnetList, Context context) {
        this.commnetList = commnetList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layotu,parent,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentRecyclerAdapter.MyViewHolder holder, int position) {
        Comments comments = commnetList.get(position);
        holder.id.setText(comments.getId());
        holder.title.setText(comments.getEmail());
    }

    @Override
    public int getItemCount() {
        return  commnetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
        }
    }
}
