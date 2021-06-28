package com.example.contentproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
TextView textViewn;
    public static final int REQUEST_READ_CONTACTS = 79;
    ProgressBar progressBar;
    HashMap<Integer,String> nameArray;
    HashMap<Integer,String> numberArray;
    String[] permissions = {Manifest.permission.READ_CONTACTS,Manifest.permission.INTERNET};
    int requestcodes = 123;
    boolean res = true;
    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewn = findViewById(R.id.textcontact);
progressBar = findViewById(R.id.progressbar);
     nameArray = new HashMap<>();
     numberArray = new HashMap<>();

        if(res == true)
        {
            getPermissions();
        }else
        {

            getname();
        }

    }

    private void getPermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED) {
                getname();
            } else {
                requestPermissions(permissions,requestcodes);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ( requestCode == requestcodes && grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            res = false;

            getname();
        } else {
            res = true;
            getPermissions();
        }

    }

    private void getname() {
         getAllContacts();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<=nameArray.size();i++)
        {


            stringBuilder.append("Name => "+nameArray.get(i)+" \n");
            stringBuilder.append("Number => "+numberArray.get(i)+ "\n\n");


        }
        progressBar.setVisibility(View.GONE);
        textViewn.setText(stringBuilder.toString());

    }

    private void getAllContacts() {


        ContentResolver cr = getContentResolver();
        int count = 0;
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,mColumnProjection,
                null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {

            while (cur != null && cur.moveToNext()) {
                count++;
                String name = cur.getString(0);
                String id = cur.getString(1);
                int status = Integer.parseInt(cur.getString(2));
                nameArray.put(count,name);
                if (status > 0) {
                    Cursor curtwo = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                            ,new String[] {id},null);

                    while (curtwo.moveToNext()) {
                        String phoneNo = curtwo.getString(curtwo.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberArray.put(count,phoneNo);

                    }
                    curtwo.close();
                }
            }

        }


    }
}