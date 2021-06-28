package com.example.bottomnaivagationbar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.bottomnaivagationbar.R


class Chat : Fragment() {

lateinit var lapoption:CheckBox
    lateinit var mobilopti:CheckBox
    lateinit var bikopti:CheckBox
    lateinit var checkbtn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lapoption = view.findViewById(R.id.laptop)
        mobilopti = view.findViewById(R.id.mobile)
        bikopti = view.findViewById(R.id.bike)
        checkbtn = view.findViewById(R.id.calculatebill)

        checkbtn.setOnClickListener {
            var bill = 0

            if(lapoption.isChecked)
            {
                bill = bill+120000
            }

            if(mobilopti.isChecked)
            {
                bill = bill+50000
            }

            if(bikopti.isChecked)
            {
                bill = bill+150000
            }
            Toast.makeText(view.context," your bill => "+bill,Toast.LENGTH_SHORT).show()
        }
    }
}