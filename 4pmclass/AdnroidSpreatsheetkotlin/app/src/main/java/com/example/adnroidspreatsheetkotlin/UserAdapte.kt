package com.example.adnroidspreatsheetkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class UserAdapte(arraylist: ArrayList<MyModelclass>, context: Context) : RecyclerView.Adapter<UserAdapte.MyViewHolder>(){

    var arraylist: ArrayList<MyModelclass>
    var context: Context

    init {
        this.arraylist = arraylist
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapte.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapte.MyViewHolder, position: Int) {
        var student = arraylist.get(position)
        holder.name.text = student.studentname
        holder.number.text = student.studentNumber
        holder.itemView.setOnClickListener {
            val intent = Intent(context, StudentDetail::class.java)
            intent.putExtra("name", student.studentname)
            intent.putExtra("number", student.studentNumber)
            intent.putExtra("id", student.studentId)
            intent.putExtra("date", student.studentdate)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
      return  arraylist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var number: TextView

        init {
            name = itemView.findViewById(R.id.list_user_name)
            number = itemView.findViewById(R.id.list_user_number)
        }
    }
}