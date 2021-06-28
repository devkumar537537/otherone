package com.example.googlespreadsheetexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.MyViewholder> {
    ArrayList<EamilModels> arraylist;
    Context context;



    public EmailAdapter(ArrayList<EamilModels> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;

    }
    @NonNull
    @Override
    public EmailAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailAdapter.MyViewholder holder, int position) {

        final EamilModels student = arraylist.get(position);

        holder.name.setText(student.getStudentemail());
        holder.number.setText(student.getStudentNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ShowUserDetail.class);
                intent.putExtra("email",student.getStudentemail());
                intent.putExtra("number",student.getStudentNumber());
                intent.putExtra("id",student.getStudentId());
                intent.putExtra("date",student.getStudentdate());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView name,number;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_user_name);
            number =itemView.findViewById(R.id.list_user_number);
        }
    }
}
