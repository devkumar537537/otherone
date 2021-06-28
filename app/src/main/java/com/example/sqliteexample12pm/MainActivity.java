package com.example.sqliteexample12pm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,surname,marks,idedit;
    Button insertbtn,showbtn,deletebtn,updatebtn;
    SqliteHellperClass sqldata ;
    String nametext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindview();
        sqldata = new SqliteHellperClass(getApplicationContext());
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = name.getText().toString();

                String surnamet_text = surname.getText().toString();
                String marks_text = marks.getText().toString();
                insertdata(name_text,surnamet_text,marks_text);
            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametext =name.getText().toString().trim();

                showdata();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = name.getText().toString();

                String surnamet_text = surname.getText().toString();
                String marks_text = marks.getText().toString();
                String userid  = idedit.getText().toString();

                updatevalues(name_text,surnamet_text,marks_text,userid);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid  = idedit.getText().toString();
                deletevalues(userid);
            }
        });
    }

    private void deletevalues(String userid) {
        int result = sqldata.deletedata(userid);
        if(result >0)
        {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "error "+result, Toast.LENGTH_SHORT).show();
        }
    }

    private void updatevalues(String name_text, String surnamet_text, String marks_text, String userid) {

        boolean reusult =  sqldata.updatadata(userid,name_text,surnamet_text,marks_text);
        if(reusult == true)
        {
            Toast.makeText(this, "upadated", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showdata() {
      Cursor cursor = sqldata.getalldata();
     //   Cursor cursor = sqldata.getalldataparticuler(nametext);
        if(cursor.getCount() == 0)
        {
            showwmessage("Error","Nothis is Present");
        }else
        {
            StringBuffer stringBuffer = new StringBuffer();

            while (cursor.moveToNext())
            {
                stringBuffer.append("ID: "+cursor.getString(0)+"\n");
                stringBuffer.append("NAME: "+cursor.getString(1)+"\n");
                stringBuffer.append("SURNAME: "+cursor.getString(2)+"\n");
                stringBuffer.append("MARKS: "+cursor.getString(3)+"\n\n");
            }
            showwmessage("Data",stringBuffer.toString());
        }

    }

    private void showwmessage(String title, String body) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(body);
        builder.setCancelable(true);
        builder.show();
    }

    private void insertdata(String name_text, String surnamet_text, String marks_text) {
        boolean result =sqldata.insertdata(name_text,surnamet_text,marks_text);
        if(result == true)
        {
            Toast.makeText(this, "value inserted", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "value insertion failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindview() {
        name = findViewById(R.id.student_name);
        surname =findViewById(R.id.student_surnmae);
        marks = findViewById(R.id.student_marks);
        insertbtn = findViewById(R.id.insert_btn);
        showbtn = findViewById(R.id.Show_btn);
        idedit = findViewById(R.id.student_id);
        updatebtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);
    }
}