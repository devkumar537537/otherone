package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var scanbtn:Button
    lateinit var movetogenerate:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanbtn = findViewById(R.id.btnScanBarCode)
        movetogenerate = findViewById(R.id.moveto_generate)
        scanbtn.setOnClickListener {
            startActivity(Intent(applicationContext,QrCodeScancActivity::class.java))
        }

        movetogenerate.setOnClickListener {
            startActivity(Intent(applicationContext,GenerateQrcode::class.java))
        }
    }
}