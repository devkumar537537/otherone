package com.example.tabactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tabactivity.fragmentst.Chat
import com.example.tabactivity.fragmentst.Home
import com.example.tabactivity.fragmentst.User

@Suppress("DEPRECATION")
class ViewPagerAdapter(var fragementmanager: FragmentManager) : FragmentPagerAdapter(fragementmanager) {
    override fun getItem(position: Int): Fragment {
        when(position)
        {
            0->{
                return Home()
            }
            1->{
                return Chat()
            }
            2->{
                return  User()
            }
        }
        return Home()
    }

    override fun getCount(): Int {
      return 3;
    }
}