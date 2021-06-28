package com.example.typesofadapters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.typesofadapters.R;
import com.example.typesofadapters.models.Recyclermodel;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.MyViewHOlder> {
    ArrayList<Recyclermodel> userlist;
    Context context;

    public RecylerAdapter(ArrayList<Recyclermodel> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecylerAdapter.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerformate,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerAdapter.MyViewHOlder holder, int position) {

        Recyclermodel recyclermodel = userlist.get(position);
        holder.recylcerimage.setImageResource(recyclermodel.getImageaddress());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = holder.editText.getText().toString();

                Toast.makeText(context, "Your name is at positon"+position+ " and name is\n "+nametext, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {

        ImageView recylcerimage;
        EditText editText;
        CardView cardView;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);

            recylcerimage = itemView.findViewById(R.id.recyclrimage);
            editText = itemView.findViewById(R.id.namedittext);
            cardView = itemView.findViewById(R.id.takenamebtn);
        }
    }
}
