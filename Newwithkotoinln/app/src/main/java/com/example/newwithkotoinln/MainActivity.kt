package com.example.newwithkotoinln

import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var samplebtn:Button
var b = 45
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        samplebtn = findViewById(R.id.showtoat)

        samplebtn.setOnClickListener {it->
            Toast.makeText(applicationContext,"this is my mssagae $b ${3+5} and rare chanc ${4/5}",Toast.LENGTH_SHORT).show()

        }

    }
}