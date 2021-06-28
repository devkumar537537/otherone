package com.example.googlespreadsheetsample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EmailAdapter(arraylist: ArrayList<EmailModel>, context: Context) :
    RecyclerView.Adapter<EmailAdapter.MyViewholder>() {

    var arraylist: ArrayList<EmailModel>
    var context: Context

    init {
        this.arraylist = arraylist
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return MyViewholder(view)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val student: EmailModel = arraylist[position]

        holder.name.text = student.studentemail;
        holder.number.text = student.studentNumber
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ShowUserDetail::class.java)
            intent.putExtra("email", student.studentemail)
            intent.putExtra("number", student.studentNumber)
            intent.putExtra("id", student.studentId)
            intent.putExtra("date", student.studentdate)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    inner class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var number: TextView

        init {
            name = itemView.findViewById(R.id.list_user_name)
            number = itemView.findViewById(R.id.list_user_number)
        }
    }


}