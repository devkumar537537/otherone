package com.example.myfiretfirebaseconcetpe.adapters;

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
import com.example.myfiretfirebaseconcetpe.R;
import com.example.myfiretfirebaseconcetpe.UpdateActivity;
import com.example.myfiretfirebaseconcetpe.models.ImageModel;
import com.example.myfiretfirebaseconcetpe.models.Models;

import org.w3c.dom.Text;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHOlder> {
    ArrayList<Models> arrayList;
    Context context;
ArrayList<ImageModel> imaglist;
    public UserAdapter(ArrayList<Models> arrayList, Context context,ArrayList<ImageModel> imaglist) {
        this.arrayList = arrayList;
        this.context = context;
        this.imaglist = imaglist;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHOlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclrerformat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHOlder holder, int position) {
Models models = arrayList.get(position);
ImageModel imageModel = imaglist.get(position);

holder.email.setText(models.getEmail());
holder.name.setText(models.getName());
        Glide.with(context).load(imageModel.getImageurl()).into(holder.imageView);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userid",models.getUserid());
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {
        TextView email,name;
        ImageView imageView;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.emailview);
            name = itemView.findViewById(R.id.nameview);
            imageView = itemView.findViewById(R.id.imageview);

        }
    }
}
