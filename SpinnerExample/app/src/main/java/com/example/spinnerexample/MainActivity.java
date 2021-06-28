package com.example.spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SpinnerMain";
    Spinner spinner;
    String[] versions = {"Choose versions","Cupcake","kitkat","Oreo","MarshMallow","lolipop","Jellbean","AndroidR","MarshMallow","lolipop","Jellbean","AndroidR"};
Button showspinner ;
String versiontext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinerexample);
showspinner = findViewById(R.id.showspintervalue);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,versions);

   arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   spinner.setAdapter(arrayAdapter);


   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           if(versions[position].equals("Choose versions")){
               Toast.makeText(MainActivity.this, "Please select versions", Toast.LENGTH_SHORT).show();
           }else
           {
               versiontext = versions[position];
           }


       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });

        showspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+versiontext );
            }
        });
    }
}