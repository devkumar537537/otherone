package com.example.firstapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapplication.R;
import com.example.firstapplication.ThirdActivity;


public class SecondFragment extends Fragment {

TextView textView;
Button movetofirst,sendbtn;
String value;
EditText numberedit,nameedit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        value = getArguments().getString("data");
       bindview(view);
        textView.setText(value);
        movetofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new FirstFragment());
                fragmentTransaction.commit();
            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberedit.getText().toString().trim();
                String name= nameedit.getText().toString().trim();

                Intent intent = new Intent(getContext(), ThirdActivity.class);
                intent.putExtra("number",number);
                intent.putExtra("name",name);
                intent.putExtra("email",value);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);




            }
        });
    }

    public void bindview(View view)
    {

        movetofirst = view.findViewById(R.id.movet_To_Fiet);
        textView = view.findViewById(R.id.textview);
        numberedit = view.findViewById(R.id.phonenumber);
        nameedit = view.findViewById(R.id.user_name);
        sendbtn = view.findViewById(R.id.sendbtn);

    }
}