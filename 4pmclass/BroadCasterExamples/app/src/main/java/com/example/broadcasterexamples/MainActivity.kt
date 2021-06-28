package com.example.broadcasterexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.RadioGroup
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    lateinit var wifiswitch:Switch
    lateinit var wifiManager: WifiManager
var myBroadcasters = MyBroadcasters()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiswitch = findViewById(R.id.wifiswitch)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        wifiswitch.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked)
            {
if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
{
    val panelintent = Intent()
    startActivityForResult(panelintent,34)
}else
{
    wifiManager.isWifiEnabled =true
    wifiswitch.text="Wifi is on"
}

            }else
            {

            }

        }


    }
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            val wifistateExtra = intent!!.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN)

            when(wifistateExtra){
                WifiManager.WIFI_STATE_ENABLED ->{
                    wifiswitch.isChecked = true
                    wifiswitch.text = "Wifi is On"
                }
                WifiManager.WIFI_STATE_DISABLED ->
                {
                    wifiswitch.isChecked =false
                    wifiswitch.text="Wifi is Off"
                }
            }

        }

    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(broadcastReceiver,intentFilter)

        val intentFilter2 = IntentFilter()
        intentFilter2.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        intentFilter2.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(myBroadcasters,intentFilter2)

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
        unregisterReceiver(myBroadcasters)
    }
}