package com.example.localandroidnotfication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    lateinit var  producenotidicationbatn:Button
    var name : String = "my_channel"
    var description= "This is my channel"
    private val NOTFICATIONID=201
    var Channel_ID ="mynofificatachannel"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        producenotidicationbatn = findViewById(R.id.producenotification)
        producenotidicationbatn.setOnClickListener {
            addNotfication()
        }
    }

    private fun addNotfication() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
       val importance = NotificationManager.IMPORTANCE_HIGH
       val mchannel = NotificationChannel(Channel_ID,name,importance)
       mchannel.description=description
       mchannel.enableLights(true)
       mchannel.enableVibration(true)
       notificationManager.createNotificationChannel(mchannel)
   }

        val builder = NotificationCompat.Builder(this,Channel_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Android")
            .setContentText("This is message of notification")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val intent = Intent(this,AfterNotificaitonActivity::class.java)
        intent.putExtra("data","data from notification")


        var pendingIntent = PendingIntent.getActivity(this,12345,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setContentIntent(pendingIntent)

        notificationManager.notify(NOTFICATIONID,builder.build())
    }
}