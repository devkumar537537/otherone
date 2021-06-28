package com.example.typesofadapter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.typesofadapter.R;
import com.example.typesofadapter.models.Items;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHOlder> {
    ArrayList<Items> userlist;
    Context context;

    public RecyclerviewAdapter(ArrayList<Items> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerformat,parent,false);
        return new MyViewHOlder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.MyViewHOlder holder, int position) {

        Items items = userlist.get(position);

        holder.textView.setText(items.getName());
        holder.imageView.setImageResource(items.getImage());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked positon => "+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;
        ImageView imageView;

        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview_row);
            button = itemView.findViewById(R.id.button);
            imageView = itemView.findViewById(R.id.imageview_row);
        }
    }
}
