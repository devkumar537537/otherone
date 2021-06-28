package com.example.servicesinkotlin

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class MyBoundeService : Service()
{
lateinit var mediaPlayer: MediaPlayer
    private val myBinder :IBinder = MyLocalBinder()

var res = false
    override fun onBind(intent: Intent?): IBinder? {
      return myBinder
    }

    inner class MyLocalBinder: Binder()
    {
        fun getservice() : MyBoundeService{
            return this@MyBoundeService

        }
    }

    fun getcurrenttime() :String{
        val dateformate = SimpleDateFormat("HH:mm:ss MM/dd//yyyy", Locale.US)
        return dateformate.format(Date())
    }
    fun startmedia()
    {
        mediaPlayer = MediaPlayer.create(this,R.raw.song)
        mediaPlayer.start()
    }
    fun pusemdei()
    {
        if(res == true)
        {
            mediaPlayer.pause()
            res = false
        }else
        {
            mediaPlayer.start()
            res =true
        }
    }

    fun reset()
    {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}