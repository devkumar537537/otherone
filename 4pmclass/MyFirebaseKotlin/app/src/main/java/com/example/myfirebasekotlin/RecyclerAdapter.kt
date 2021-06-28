package com.example.myfirebasekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirebasekotlin.models.RegisterModel


class RecyclerAdapter(var arraylist:MutableList<RegisterModel>,var context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyviewhOlder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyviewhOlder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.layoutrecylerformat,parent,false)
        return MyviewhOlder(view)
    }



    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyviewhOlder, position: Int) {

        var recyclermod: RegisterModel = arraylist.get(position)
//      holder.imageView.setImageResource(recyclermod.imagerulr)
        holder.numbettext.text = recyclermod.number
        holder.emailtext.text = recyclermod.email

        if(recyclermod.imageurl.equals("default"))
        {
            holder.imageView.setImageResource(R.mipmap.ic_launcher)
        }else
        {
            Glide.with(context).load(recyclermod.imageurl).into(holder.imageView)
        }

    }

    class MyviewhOlder(var itemview: View) : RecyclerView.ViewHolder(itemview)  {

         var imageView:ImageView
      var emailtext:TextView
        var numbettext: TextView

        init {
            imageView = itemview.findViewById(R.id.recyclrimage);
          emailtext = itemview.findViewById(R.id.emailviewrecycler)
            numbettext = itemview.findViewById(R.id.numberviewrecycler)
        }
    }
}