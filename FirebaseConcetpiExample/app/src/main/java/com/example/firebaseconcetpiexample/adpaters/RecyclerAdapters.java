package com.example.firebaseconcetpiexample.adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebaseconcetpiexample.R;
import com.example.firebaseconcetpiexample.UpdateInfoActiity;
import com.example.firebaseconcetpiexample.models.ImageModel;
import com.example.firebaseconcetpiexample.models.Usermodel;

import java.util.ArrayList;

public class RecyclerAdapters extends RecyclerView.Adapter<RecyclerAdapters.MyViewHolder> {
    ArrayList<ImageModel> imaglist;
    ArrayList<Usermodel> userlist;
    Context context;

    public RecyclerAdapters(ArrayList<ImageModel> imaglist, ArrayList<Usermodel> userlist, Context context) {
        this.imaglist = imaglist;
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.producformat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapters.MyViewHolder holder, int position) {

        ImageModel imageModel = imaglist.get(position);
        Usermodel usermodel = userlist.get(position);

        holder.email.setText(usermodel.getEmail());
        holder.number.setText(usermodel.getNumber());
        Glide.with(context).load(imageModel.getImagurl()).into(holder.profileimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateInfoActiity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userid",usermodel.getUserid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email,number;
        ImageView profileimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.namedittext);
            number = itemView.findViewById(R.id.numberlayout);
            profileimage = itemView.findViewById(R.id.recyclrimage);
        }
    }
}
