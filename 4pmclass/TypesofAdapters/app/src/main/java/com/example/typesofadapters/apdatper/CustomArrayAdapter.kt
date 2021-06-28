package com.example.typesofadapters.apdatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.typesofadapters.R
import com.example.typesofadapters.modelclass.Item

class CustomArrayAdapter(var textviewresourcId:Int,var imagelist: MutableList<Item>,var contextloc:Context) :
    ArrayAdapter<Item>(contextloc,textviewresourcId,imagelist){

    override fun getCount(): Int {
        return imagelist.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val inflater = contextloc.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.arrayformat,null)

        var imageView = view.findViewById<ImageView>(R.id.imagelist)
        var textView = view.findViewById<TextView>(R.id.nametext)

        var item = imagelist.get(position)

        imageView.setImageResource(item.imageurl)
        textView.text = item.username


        return view
    }
}