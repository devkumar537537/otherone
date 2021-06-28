package com.example.broadcasterexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcasters : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val actionString = intent!!.action
        Toast.makeText(context, "action $actionString", Toast.LENGTH_LONG).show()
        if (actionString == "android.intent.action.AIRPLANE_MODE") {
            Toast.makeText(context, "You implemented global recevier", Toast.LENGTH_SHORT).show()
        }
    }

}