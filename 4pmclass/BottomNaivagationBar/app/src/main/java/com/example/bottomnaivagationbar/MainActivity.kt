package com.example.bottomnaivagationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnaivagationbar.fragments.Chat
import com.example.bottomnaivagationbar.fragments.Home
import com.example.bottomnaivagationbar.fragments.User

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(Home())
     bottomNavigationView = findViewById(R.id.bottomnavigationview)
   bottomNavigationView.setOnNavigationItemSelectedListener { item ->

       when(item.itemId)
       {
           R.id.homeicon->{
               openFragment(Home())
               return@setOnNavigationItemSelectedListener true
           }
           R.id.chaticon->
           {
               openFragment(Chat())
               return@setOnNavigationItemSelectedListener true
           }
           R.id.usericon->{
               openFragment(User())
               return@setOnNavigationItemSelectedListener true
           }
       }
       true
   }
    }

    private fun openFragment(fragment: Fragment) {
 var fragmentManager = supportFragmentManager
        var fragmentraction = fragmentManager.beginTransaction()
        fragmentraction.replace(R.id.fragmencontainer,fragment).commit()
    }
}