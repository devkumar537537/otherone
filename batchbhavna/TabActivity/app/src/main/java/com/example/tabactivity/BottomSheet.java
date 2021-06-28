package com.example.tabactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet  extends BottomSheetDialogFragment {
EditText emailedit,passwordedit;
Button loginbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.bottomsheet,container,false);
emailedit = view.findViewById(R.id.emailayout);
passwordedit = view.findViewById(R.id.passwordlayout);
loginbtn = view.findViewById(R.id.loginbtn);
loginbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email_text = emailedit.getText().toString();
        String password_text = passwordedit.getText().toString();

        Toast.makeText(view.getContext(), "email => "+email_text+" \n passowrd => "+password_text, Toast.LENGTH_SHORT).show();
    }
});


        return view;
    }
}
