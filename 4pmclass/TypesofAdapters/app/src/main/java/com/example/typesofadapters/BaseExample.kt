package com.example.typesofadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.typesofadapters.apdatper.CustomBaseAdapter
import com.example.typesofadapters.modelclass.Item

class BaseExample : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var imaglist:MutableList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_example)
        listView = findViewById(R.id.listviewbase)
        imaglist = ArrayList<Item>()

        imaglist.add(Item(R.drawable.download,"First Name"))
        imaglist.add(Item(R.drawable.naturetwo,"Second Name"))
        imaglist.add(Item(R.drawable.right,"Third Name"))
        imaglist.add(Item(R.drawable.sample,"Fourth Name"))
        imaglist.add(Item(R.drawable.download,"First Name"))
        imaglist.add(Item(R.drawable.naturetwo,"Second Name"))
        imaglist.add(Item(R.drawable.right,"Third Name"))

        var adapter = CustomBaseAdapter(imaglist,applicationContext)
        listView.adapter = adapter
    }
}