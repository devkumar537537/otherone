package com.example.myfirstapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FirstFragment : Fragment() {

lateinit var button: Button

var TAG:String = "First Fragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.e(TAG,"OnCreadtVeiw Called")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"OnCreadtVeiwCreadted Called")
        button = view.findViewById(R.id.move_to_seocndFragment);
        button.setOnClickListener {
            val fragment = SecondFragment()
            var bundle = Bundle()
            bundle.putString("first","Data from First Fragment")
            fragment.arguments = bundle
            val fragmentManager = fragmentManager
            val fragmentTraction = fragmentManager!!.beginTransaction();
            fragmentTraction.replace(R.id.fragmentcontainer,fragment);
            fragmentTraction.commit()
        }
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