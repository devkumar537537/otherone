package com.example.typesofadapters.apdatper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SimpleAdapter
import android.widget.Toast
import com.example.typesofadapters.R

class CustomSimpleAdapter(var context: Context,var arrylist:ArrayList<HashMap<String,String>>,var resourc:Int,from:Array<String> ,
                          to : IntArray) : SimpleAdapter(context,arrylist,resourc,from,to){
    override fun getCount(): Int {
        return arrylist.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view =super.getView(position, convertView, parent)
            var button =  view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            sum(position,position)
        }
        return  view;
    }
    fun sum(i:Int,j:Int)
    {
        Toast.makeText(context,"sum is ${i+j}",Toast.LENGTH_SHORT).show()
    }
}