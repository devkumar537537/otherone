package com.example.googlespreadsheets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    ArrayList<Student> arraylist;
    Context context;



    public StudentAdapter(ArrayList<Student> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;

    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {

        final Student student = arraylist.get(position);

        holder.name.setText(student.getStudentemail());
        holder.number.setText(student.getStudentNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,StudentDetails.class);
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.list_user_name);
            number =itemView.findViewById(R.id.list_user_number);
        }
    }
}
