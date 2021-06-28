package com.example.contentproviders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS = 79;
ProgressBar progressBar;
    HashMap<Integer,String> nameArray;
    HashMap<Integer,String> numberArray;
    TextView textView ;
    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    String[] permissions = {Manifest.permission.READ_CONTACTS,Manifest.permission.INTERNET};
    int requestcodes = 123;
    boolean res = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.textcontact);
        numberArray = new HashMap<>();
        nameArray = new HashMap<>();

if(res == true)
{
    getpermissions();
}else
{

    getname();
}

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

                if ( requestCode == requestcodes && grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    res = false;

                 getname();
                } else {
                    res = true;
                 getpermissions();
                }


        }

    private HashMap<Integer, String> getAllContacts() {
        HashMap<Integer,String> nameList = new HashMap<>();

        int count = 0;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,mColumnProjection,
                null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                count++;
                String name = cur.getString(0);
                String id = cur.getString(1);
                int status = Integer.parseInt(cur.getString(2));
                nameList.put(count,name);

                if (status > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberArray.put(count,phoneNo);

                    }
                    pCur.close();
                }
            }
        }
           return nameList;
    }
    private void getname()
    {
        nameArray = getAllContacts();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<=nameArray.size();i++)
        {


            stringBuilder.append("Name => "+nameArray.get(i)+" \n");
            stringBuilder.append("Number => "+numberArray.get(i)+ "\n\n");


        }
        progressBar.setVisibility(View.GONE);
        textView.setText(stringBuilder.toString());
    }
    private void getpermissions()
    {
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
}