package com.example.bottomnavigationexample.fragmentst;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.bottomnavigationexample.R;


public class FirstFragment extends Fragment {


RatingBar ratingBar;
Button ratingbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ratingBar = view.findViewById(R.id.ratingbar);
        ratingbtn = view.findViewById(R.id.ratingbarbtn);
        ratingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ratingvalue = ratingBar.getRating()+"";
                Toast.makeText(getContext(), "value is "+ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}