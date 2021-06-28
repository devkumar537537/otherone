package com.example.firstapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.firstapplication.R;


public class FirstFragment extends Fragment {
Button movetobtn,checkbtn;

RadioGroup radioGroup;

    private static final String TAG = "FirstFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG, "onCreateView: " );
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: " );
        movetobtn = view.findViewById(R.id.movetofirst);
       radioGroup = view.findViewById(R.id.radiogroup);
       checkbtn = view.findViewById(R.id.check);

       checkbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int selecteid = -1;
                selecteid = radioGroup.getCheckedRadioButtonId();

              RadioButton radioButton = view.findViewById(selecteid);



               if(selecteid < 0)
               {
                   Toast.makeText(getContext(),"select atleast one option",Toast.LENGTH_SHORT).show();
               }else
               {
                   String text = radioButton.getText().toString();
                   if(text.equals("less18")){
                       Toast.makeText(getContext(), "You are not eligible for voting", Toast.LENGTH_SHORT).show();
                   }else
                   {
                       Toast.makeText(getContext(), "You are  eligible for voting", Toast.LENGTH_SHORT).show();

                   }
               }
           }
       });
        movetobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Fragment fragment = new SecondFragment();
              Bundle args = new Bundle();
              args.putString("first","Data from Firstfragment");
              fragment.setArguments(args);


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: " );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }
}