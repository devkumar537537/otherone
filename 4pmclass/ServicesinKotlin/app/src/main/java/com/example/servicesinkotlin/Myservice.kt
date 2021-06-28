package com.example.servicesinkotlin

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Myservice : Service(){

   lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
      return null
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Service started",Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(this,R.raw.song)
        mediaPlayer.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_SHORT).show()
        mediaPlayer.stop()
    }

    fun getcurrenttime() :String{
        val dateformate = SimpleDateFormat("HH:mm:ss MM/dd//yyyy", Locale.US)
        mediaPlayer.stop()
        return dateformate.format(Date())

    }
}