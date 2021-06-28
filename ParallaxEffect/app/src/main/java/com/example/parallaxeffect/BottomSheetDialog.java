package com.example.parallaxeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    ExtendedFloatingActionButton lognbtn;
    TextInputEditText textInputEditText;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomseet,container,false);

        lognbtn = view.findViewById(R.id.loginbtn);
        textInputEditText = view.findViewById(R.id.emaillayoutbottom);
        lognbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textInputEditText.getText().toString();
                Toast.makeText(view.getContext(), "email "+value, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return view;


    }
}
