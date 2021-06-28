package com.example.parallaxeffect.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parallaxeffect.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class FirstFragment extends Fragment {

ExtendedFloatingActionButton butt;
TextInputEditText emailedit,passwordedit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectexmtl(view);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String emailt_text = emailedit.getText().toString();
             String password_text  = passwordedit.getText().toString();

             Snackbar.make(v,"email => "+emailt_text ,Snackbar.LENGTH_SHORT)
                     .setAction("Password", new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Snackbar.make(v,"password => "+password_text,Snackbar.LENGTH_SHORT).show();
                         }
                     })
                     .show();
            }
        });

    }

    private void connectexmtl(View view) {
        butt =view.findViewById(R.id.logintbnt);
        emailedit = view.findViewById(R.id.emailledit);
        passwordedit = view.findViewById(R.id.password_edit);
    }
}