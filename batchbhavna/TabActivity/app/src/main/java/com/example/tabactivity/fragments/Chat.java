package com.example.tabactivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tabactivity.R;


public class Chat extends Fragment {
RatingBar ratingBar;
Button pickrationg;
RelativeLayout relativeLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ratingBar = view.findViewById(R.id.ratinbar);
        pickrationg = view.findViewById(R.id.pickratingbtn);
       
        pickrationg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float value = ratingBar.getRating();

                if( value <= 2)
                {
                    Toast.makeText(getContext(), "you chooose it wrong rating => "+value, Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getContext(), "Fine rating => "+value, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}