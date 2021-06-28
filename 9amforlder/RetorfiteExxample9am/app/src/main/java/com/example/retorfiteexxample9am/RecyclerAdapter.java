package com.example.retorfiteexxample9am;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewholder> {


    List<PostModel> postList;
    Context context;

    public RecyclerAdapter(List<PostModel> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layotu,parent,false);

        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewholder holder, int position) {
        PostModel post = postList.get(position);
        holder.id.setText(post.getId());
        holder.title.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView id,title;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
        }
    }
}
