package com.example.firstapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import com.example.firstapplication.R;


public class FirstFragment extends Fragment {
Button movebtn;
    String TAG = "FirstFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"OnCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"OnViewCreated");
        movebtn = view.findViewById(R.id.move_To_second);
        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putString("first","Value from first fragment");

                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"OnActivtyCreatedView");
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG,"OnAttachView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"OnDetachView");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"OnDestroyView");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"OnDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"OnStartView");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"OnStopView");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"OnPuaseView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"OnResumeView");
    }

}