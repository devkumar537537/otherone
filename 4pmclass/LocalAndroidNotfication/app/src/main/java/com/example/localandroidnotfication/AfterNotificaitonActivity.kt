package com.example.localandroidnotfication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AfterNotificaitonActivity : AppCompatActivity() {
    lateinit var notificatiodata: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_notificaiton)

        val value = intent.getStringExtra("data");
        notificatiodata = findViewById(R.id.textView)
        notificatiodata.text = value
    }
}