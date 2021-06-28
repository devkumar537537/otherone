package com.example.servicesinkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var startbtn:Button
    lateinit var stopbtn:Button
    lateinit var getbtton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startbtn = findViewById(R.id.startbtn)
        stopbtn = findViewById(R.id.stopbtn)
getbtton = findViewById(R.id.gettimebtn)
        startbtn.setOnClickListener {
       startService(Intent(this@MainActivity,Myservice::class.java))
        }

        getbtton.setOnClickListener {
            var myservice = Myservice()
            myservice.getcurrenttime()
            Toast.makeText(applicationContext,"time is "+myservice.getcurrenttime(),Toast.LENGTH_SHORT).show()

        }
        stopbtn.setOnClickListener {
            stopService(Intent(this@MainActivity,Myservice::class.java))
        }
    }
}