package com.example.typesofadapters.apdatper

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.typesofadapters.R
import com.example.typesofadapters.modelclass.Item

class CustomBaseAdapter (var userdata:MutableList<Item>,var context: Context) : BaseAdapter(){
    private val list =colors()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.arrayformat,null)
        var imageView = view.findViewById<ImageView>(R.id.imagelist)
        var textView = view.findViewById<TextView>(R.id.nametext)
        var button = view.findViewById<Button>(R.id.button)

        var items:Item = userdata.get(position)

        textView.text = items.username
        imageView.setImageResource(items.imageurl)


        button.setOnClickListener {
            Toast.makeText(context,"clicked ${list[position].first}",Toast.LENGTH_SHORT).show()

            var activity = parent?.context as Activity

            var viewGroup = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
            viewGroup.setBackgroundColor(list[position].second)
        }
        return view
    }

    override fun getItem(position: Int): Any {
       return list[position]
    }

    override fun getItemId(position: Int): Long {
    return position.toLong()
    }

    override fun getCount(): Int {
        return userdata.size
    }

    private fun colors():List<Pair<String,Int>>{
        return listOf(
            Pair("RED",Color.parseColor("#FF0000")),
            Pair("BLUE",Color.parseColor("#0000FF")),
            Pair("GREEN",Color.parseColor("#008000")),
            Pair("PURPLE",Color.parseColor("#FF00FF")),
            Pair("YELLOW",Color.parseColor("#FFFF00")),
            Pair("VIOLET",Color.parseColor("#8A2BE2")),
            Pair("BLACK",Color.parseColor("#000000"))

        )
    }
}