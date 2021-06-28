package com.example.myfirstapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class SecondFragment : Fragment() {
lateinit var button:Button
    lateinit var movetorelative:Button
    lateinit var textView: TextView
var strinvalue :String? = null
    var TAG :String = "Second Fragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e(TAG,"OnCreateView Called")

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        strinvalue = arguments!!.getString("first")
        textView= view.findViewById(R.id.dataget)
        textView.text = strinvalue
        button = view.findViewById(R.id.move_to_FirstFragment)
        button.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTraction = fragmentManager!!.beginTransaction();
            fragmentTraction.replace(R.id.fragmentcontainer,FirstFragment());
            fragmentTraction.commit()
        }
        movetorelative = view.findViewById(R.id.move_to_relativelayout)
        movetorelative.setOnClickListener {
            val intent = Intent(view.context,RelativeExamples::class.java)
            startActivity(intent)
        }
        Log.e(TAG,"OnView Created Called")

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG,"OnAttach Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"OnDestroy");
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"OnStart");
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"OnDResumen");
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"OnPause");
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"OnStopd");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG,"OnDestroyView");
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG,"OnDettach");
    }
}