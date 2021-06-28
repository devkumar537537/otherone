package com.example.gesturesexamples

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SmsActivity : AppCompatActivity() {
    lateinit var numbereid:EditText
    lateinit var msgedit:EditText
    lateinit var sendsmsmbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        numbereid = findViewById(R.id.user_sms_number)
        msgedit = findViewById(R.id.sms_body)
        sendsmsmbtn = findViewById(R.id.send_sms_btn)
        sendsmsmbtn.setOnClickListener {
            val numbertext = numbereid.text.toString()
            val msgtext = msgedit.text.toString()

            sendmsge(numbertext,msgtext)
        }
    }

    private fun sendmsge(numbertext: String, msgtext: String) {
        val smsintent = Intent(
            applicationContext,
            SmsActivity::class.java
        )

        val pendingIntent =
            PendingIntent.getActivity(applicationContext, 34, smsintent, 0)
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(numbertext, null, msgtext, pendingIntent, null)
        Toast.makeText(
            applicationContext,
            "Messages Send Successfully",
            Toast.LENGTH_SHORT
        ).show()
    }
}