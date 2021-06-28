package com.example.qrcodeinkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var movetoBarCode: Button
    lateinit var movetogeneratecode: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movetoBarCode = findViewById(R.id.btnScanBarCode)
        movetogeneratecode = findViewById(R.id.moveto_generate)
        movetogeneratecode.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GenerateBarcode::class.java
                )
            )
        })
        movetoBarCode.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    BarCodeScannerActivity::class.java
                )
            )
        })
    }
}