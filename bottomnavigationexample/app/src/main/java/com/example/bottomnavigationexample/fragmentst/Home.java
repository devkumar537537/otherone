package com.example.bottomnavigationexample.fragmentst;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.bottomnavigationexample.R;


public class Home extends Fragment {

   Button submintdatabtn;
   TextView datetextview;
   DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connestcsmlj(view);

        datetextview.setText("Current Date => "+visulisecurrentdate());
        submintdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetextview.setText("Update Date => "+visulisecurrentdate());
            }
        });
    }

    private String visulisecurrentdate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(datePicker.getDayOfMonth() +" /");
        stringBuilder.append((datePicker.getMonth()+1) +" /");
        stringBuilder.append(datePicker.getYear());
        return stringBuilder.toString();
    }

    private void connestcsmlj(View view)
    {

        submintdatabtn = view.findViewById(R.id.submitdate);
        datetextview = view.findViewById(R.id.currentdate);
        datePicker = view.findViewById(R.id.datepicklayout);
    }
}