package com.example.webviewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SpinnerExample extends AppCompatActivity {
Spinner spinner;
String[] countrylist = {"Select countries","India","USA","BnglaDesh","ShriLanka","Nepal","UAE","Pakistaan","AfgnistaaN"};


Button prouducebtn;
EditText namedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_example);
        spinner = findViewById(R.id.spinner);
        prouducebtn = findViewById(R.id.producecustomstoast);
        namedit = findViewById(R.id.namedit);

        prouducebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = namedit.getText().toString();

                Snackbar.make(v,"name => "+nametext,Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                 Toast tone = new Toast(getApplicationContext());
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                               View  view = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.customtoast));

                               tone.setView(view);
                               tone.setGravity(Gravity.CENTER_HORIZONTAL,200,2000);
                               tone.setDuration(Toast.LENGTH_LONG);
                               tone.show();

                            }
                        }).show();
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,countrylist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectvalue = countrylist[position];

                if(selectvalue.equals("Select countries")){
                    Toast.makeText(SpinnerExample.this, "Select the country", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(SpinnerExample.this, "selected "+selectvalue, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}