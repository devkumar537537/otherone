package com.example.servicesinkotlin

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class BunddeActivity : AppCompatActivity() {

    lateinit var dateview:TextView
    lateinit var start:Button
    lateinit var pause:Button
    lateinit var reset:Button
var isbound = false
    var myservice :MyBoundeService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bundde)
        conncect()
val intent = Intent(this@BunddeActivity,MyBoundeService::class.java)
        bindService(intent,myconnection, Context.BIND_AUTO_CREATE)
start.setOnClickListener {
    val datetext = myservice!!.getcurrenttime()
    dateview.text = datetext
               myservice!!.startmedia()
}
        pause.setOnClickListener {
            myservice!!.pusemdei()
        }

reset.setOnClickListener {
    myservice!!.reset()
}

    }
    fun conncect(){
        dateview = findViewById(R.id.timeview)
        start = findViewById(R.id.showtimebtn)
        pause = findViewById(R.id.puasebtn)
        reset = findViewById(R.id.resetbtn)
    }

    private val myconnection : ServiceConnection= object :ServiceConnection{

        override fun onServiceDisconnected(name: ComponentName?) {
          isbound = false;
            Toast.makeText(myservice,"Service connection failed",Toast.LENGTH_SHORT).show()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            val binder: MyBoundeService.MyLocalBinder = service as MyBoundeService.MyLocalBinder
            myservice = binder.getservice()
            isbound = true;
            Toast.makeText(myservice,"Service  conncectedd",Toast.LENGTH_SHORT).show()

        }

    }
}