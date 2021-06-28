package com.example.gesturesexamples

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class TelephonyManager : AppCompatActivity() {
    lateinit var numberedit: EditText
    lateinit var callbtn: Button
    lateinit var movesms: Button
    lateinit var moveemail: Button
 var permissions = arrayOf(android.Manifest.permission.CALL_PHONE,android.Manifest.permission.READ_SMS,
     android.Manifest.permission.SEND_SMS)
    var requestcode = 45

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telephony_manager)
        conncectxml()
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,requestcode)
            }
        }
        callbtn.setOnClickListener {
            var number = numberedit.text.toString();
            val callintent = Intent(Intent.ACTION_CALL)

            callintent.data = Uri.parse("tel:$number")
            callintent.putExtra("simSlot", 1)
            startActivity(callintent)
        }
        movesms.setOnClickListener {
            startActivity(Intent(applicationContext,SmsActivity::class.java))
        }
    }

    fun conncectxml(){
      numberedit = findViewById(R.id.usern_number)
        callbtn = findViewById(R.id.sendCall)
        moveemail = findViewById(R.id.move_to_email)
        movesms = findViewById(R.id.move_t0_sms)
    }
}