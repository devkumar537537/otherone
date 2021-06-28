package com.example.typesofadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.typesofadapters.apdatper.CustomSimpleAdapter

class SimpleAdapterExample : AppCompatActivity() {
    lateinit var listView: ListView

    var countrylist = arrayOf("India","Nepal","USA","Canada","Australia","UK","Newzland");

    var images = intArrayOf(R.drawable.download,R.drawable.right,R.drawable.sample,R.drawable.third,R.drawable.naturetwo,R.drawable.weather,R.drawable.download)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_adapter_example)
        listView = findViewById(R.id.listviewsimple);

        var arrayList = ArrayList<HashMap<String,String>>()
        for( i in images.indices)
        {
            var hasmap = HashMap<String,String>()
            hasmap.put("name",countrylist[i])
            hasmap.put("images","${images[i]}")

            arrayList.add(hasmap)
        }

        var from = arrayOf("name","images")

        var to = intArrayOf(R.id.nametext,R.id.imagelist)

        var customSimpleAdapter = CustomSimpleAdapter(applicationContext,arrayList,R.layout.arrayformat,from,to)

        listView.adapter = customSimpleAdapter

        listView.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(applicationContext,"clicked ${countrylist[position]}",Toast.LENGTH_SHORT).show()

        }


    }
}