package com.example.typesofadapters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.typesofadapters.apdatper.CustomArrayAdapter
import com.example.typesofadapters.modelclass.Item

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var imaglist:MutableList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        listView = findViewById(R.id.listviearry)
        imaglist = ArrayList()

        imaglist.add(Item(R.drawable.download,"First Name"))
        imaglist.add(Item(R.drawable.naturetwo,"Second Name"))
        imaglist.add(Item(R.drawable.right,"Third Name"))
        imaglist.add(Item(R.drawable.sample,"Fourth Name"))
        imaglist.add(Item(R.drawable.download,"First Name"))
        imaglist.add(Item(R.drawable.naturetwo,"Second Name"))
        imaglist.add(Item(R.drawable.right,"Third Name"))
        imaglist.add(Item(R.drawable.sample,"Fourth Name"))

        val adapter = CustomArrayAdapter(R.layout.arrayformat,imaglist,applicationContext)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->


            var intent = Intent(applicationContext,SimpleAdapterExample::class.java)
            startActivity(intent)
        }

    }
}