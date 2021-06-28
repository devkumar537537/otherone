package com.example.tabactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tablayhout)
        viewPager = findViewById(R.id.viewpager)

        var apapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = apapter
        tabLayout.setupWithViewPager(viewPager)
        seticon()
    }

    private fun seticon() {
var textview = LinearLayout.inflate(this,R.layout.tabsname,null) as TextView
       textview.text = "Home"
        textview.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.home,0,0,0)
        tabLayout.getTabAt(0)!!.setCustomView(textview)

        var textview1 = LinearLayout.inflate(this,R.layout.tabsname,null) as TextView
        textview1.text = "Chat"
        textview1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.chat,0,0,0)
        tabLayout.getTabAt(1)!!.setCustomView(textview1)

        var textview2 = LinearLayout.inflate(this,R.layout.tabsname,null) as TextView
        textview2.text = "User"
        textview2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.user,0,0,0)
        tabLayout.getTabAt(2)!!.setCustomView(textview2)

    }
}