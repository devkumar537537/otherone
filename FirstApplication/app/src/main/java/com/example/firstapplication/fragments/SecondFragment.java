package com.example.firstapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstapplication.R;


public class SecondFragment extends Fragment {

String TAG = "SecondFragment";
TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"OnCreatedView");
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"OnViewCreated");
        String value = getArguments().getString("first");
        textView = view.findViewById(R.id.valuetext);
        textView.setText(value);
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
        Log.e(TAG,"OnDestroyView");
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