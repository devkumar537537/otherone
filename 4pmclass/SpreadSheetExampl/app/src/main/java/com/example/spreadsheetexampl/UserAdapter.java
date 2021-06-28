package com.example.spreadsheetexampl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    ArrayList<MyModel> arraylist;
    Context context;

    public UserAdapter(ArrayList<MyModel> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserAdapter.MyViewHolder holder, int position) {
MyModel myModel = arraylist.get(position);
holder.name.setText(myModel.getUsername());
holder.number.setText(myModel.getUserNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,UserDetail.class);
                intent.putExtra("name",myModel.getUsername());
                intent.putExtra("number",myModel.getUserNumber());
                intent.putExtra("id",myModel.getUserId());
                intent.putExtra("date",myModel.getUserdate());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,number;
        public MyViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_user_name);
            number =itemView.findViewById(R.id.list_user_number);
        }
    }
}
