package com.example.contentproviders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS = 79;
    ListView list;
    HashMap<Integer,String> nameArray;
    HashMap<Integer,String> numberArray;
  TextView textView ;
    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME + " = ?";

    private String[] mSelectionArguments = new String[]{"Durgesh"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 textView = findViewById(R.id.textcontact);
        numberArray = new HashMap<>();
        mobileArray = new HashMap<>();

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                mobileArray = getAllContacts();
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=1;i<=mobileArray.size();i++)
                {
                    stringBuilder.append("Name => "+mobileArray.get(i)+" \n");
                    stringBuilder.append("Number => "+numberArray.get(i)+ "\n\n");
                }
             textView.setText(stringBuilder.toString());
            } else {
                requestPermission();
            }
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mobileArray = getAllContacts();
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i=1;i<=mobileArray.size();i++)
                    {
                        stringBuilder.append("Name => "+mobileArray.get(i)+" \n");
                        stringBuilder.append("Number => "+numberArray.get(i)+ "\n\n");
                    }
                    textView.setText(stringBuilder.toString());
                } else {
                    // permission denied,Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
    private HashMap<Integer,String> getAllContacts() {
        HashMap<Integer,String> nameList = new HashMap<>();
        int count = 0;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,mColumnProjection, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                   count++;
                String name = cur.getString(0);
                String id = cur.getString(1);
                int status = Integer.parseInt(cur.getString(2));
                nameList.put(count,name);

                if (status > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberArray.put(count,phoneNo);

                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList;
    }
    }
