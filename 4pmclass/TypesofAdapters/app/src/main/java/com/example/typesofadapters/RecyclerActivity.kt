package com.example.typesofadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typesofadapters.apdatper.RecyclerAdapter
import com.example.typesofadapters.modelclass.RecyclerMode

class RecyclerActivity : AppCompatActivity() {
    lateinit var recylclerview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycler)
        var imagelist: MutableList<RecyclerMode>  = ArrayList<RecyclerMode>()
        recylclerview = findViewById(R.id.recyclerview)

        var layoutmanager = LinearLayoutManager(applicationContext)
        recylclerview.layoutManager = layoutmanager

        imagelist.add(RecyclerMode(R.drawable.download))
        imagelist.add(RecyclerMode(R.drawable.right))
        imagelist.add(RecyclerMode(R.drawable.naturetwo))
        imagelist.add(RecyclerMode(R.drawable.sample))
        imagelist.add(RecyclerMode(R.drawable.third))
        imagelist.add(RecyclerMode(R.drawable.weather))
        imagelist.add(RecyclerMode(R.drawable.download))
        imagelist.add(RecyclerMode(R.drawable.right))
        imagelist.add(RecyclerMode(R.drawable.naturetwo))
        imagelist.add(RecyclerMode(R.drawable.sample))
        imagelist.add(RecyclerMode(R.drawable.third))
        imagelist.add(RecyclerMode(R.drawable.weather))
        imagelist.add(RecyclerMode(R.drawable.download))
        imagelist.add(RecyclerMode(R.drawable.right))
        imagelist.add(RecyclerMode(R.drawable.naturetwo))
        imagelist.add(RecyclerMode(R.drawable.sample))
        imagelist.add(RecyclerMode(R.drawable.third))
        imagelist.add(RecyclerMode(R.drawable.weather))

        var reycyleradatper = RecyclerAdapter(imagelist,applicationContext)
        recylclerview.adapter = reycyleradatper



    }
}