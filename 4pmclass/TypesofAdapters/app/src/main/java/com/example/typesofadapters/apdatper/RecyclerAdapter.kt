package com.example.typesofadapters.apdatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.typesofadapters.R
import com.example.typesofadapters.modelclass.RecyclerMode

class RecyclerAdapter(var arraylist:MutableList<RecyclerMode>,var context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyviewhOlder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyviewhOlder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.layoutrecylerformat,parent,false)
        return MyviewhOlder(view)
    }



    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyviewhOlder, position: Int) {

        var recyclermod: RecyclerMode = arraylist.get(position)
      holder.imageView.setImageResource(recyclermod.imagerulr)
        holder.button.setOnClickListener {
            var nametext =holder.editText.text.toString()
            Toast.makeText(context," message => $nametext",Toast.LENGTH_SHORT).show()
        }
    }

    class MyviewhOlder(var itemview: View) : RecyclerView.ViewHolder(itemview)  {

         var imageView:ImageView
        var editText: EditText
         var button: Button

        init {
            imageView = itemview.findViewById(R.id.recyclrimage);
            editText = itemview.findViewById(R.id.namedittext)
            button = itemview.findViewById(R.id.simplebtn)
        }
    }
}