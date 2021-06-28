package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
private lateinit var emailedit:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.samplesbtn);
        emailedit = findViewById(R.id.email_layout);
        button.setOnClickListener {
            var email_text = emailedit.text.toString().trim()

            Toast.makeText(applicationContext,"message $email_text",Toast.LENGTH_SHORT).show()
//            var intent = Intent(applicationContext,RelativeExamples::class.java);
//            startActivity(intent)
        }
        var fragmentManager = supportFragmentManager
        var fragmentranction = fragmentManager.beginTransaction()
        fragmentranction.add(R.id.fragmentcontainer,FirstFragment())
        fragmentranction.commit()

    }


}