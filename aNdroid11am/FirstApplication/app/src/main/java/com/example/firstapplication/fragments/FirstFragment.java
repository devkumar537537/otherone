package com.example.firstapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firstapplication.R;


public class FirstFragment extends Fragment {
    ListView listView;
    Button movetosecond;
    EditText emailedit;
    String[] countrylist = {"India", "UK", "USA", "UAE", "CANADA", "jermni", "nepal", "China"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailedit = view.findViewById(R.id.email);

        listView = view.findViewById(R.id.listview);


        movetosecond = view.findViewById(R.id.moveto_second_fragmnet);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countrylist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
        movetosecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = emailedit.getText().toString().trim();
                if (TextUtils.isEmpty(email_text)) {
                    Toast.makeText(getContext(), "please eneter email", Toast.LENGTH_SHORT).show();
                } else {
                    Fragment fragment = new SecondFragment();
                    Bundle valuesbundle = new Bundle();

                    valuesbundle.putString("data", email_text);

                    fragment.setArguments(valuesbundle);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                }


            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.firstitem)
        {
            Toast.makeText(getContext(), "clicked : "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id == R.id.secondItem)
        {
            Toast.makeText(getContext(), "clicked :"+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
