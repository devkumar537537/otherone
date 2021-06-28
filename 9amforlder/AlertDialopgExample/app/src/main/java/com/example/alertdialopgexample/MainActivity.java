package com.example.alertdialopgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button,getValuefromradiobtn;
ProgressDialog progressDialog;
int progressstatus = 0;
int filesize =0;
private Handler progressdialHandler = new Handler();
RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.createprogresbtn);
  radioGroup = findViewById(R.id.radiogroup);
  getValuefromradiobtn = findViewById(R.id.getvaluefromradiobtn);
  getValuefromradiobtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          int id = radioGroup.getCheckedRadioButtonId();
          RadioButton radioButton = findViewById(id);
          if( id == -1)
          {
              Toast.makeText(MainActivity.this, "NOthing is selected", Toast.LENGTH_SHORT).show();
          }else
          {

              Toast.makeText(MainActivity.this, "value is "+radioButton.getText(), Toast.LENGTH_SHORT).show();
          }
      }
  });
        showprogressbar();
    }

    private void showprogressbar() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Updating the File....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.show();

                progressstatus = 0;
                filesize =0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressstatus < 100)
                        {
                            progressstatus = getvalue();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progressdialHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progressstatus);
                                }
                            });
                        }
                        if(progressstatus >= 100)
                        {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                            startActivity(new Intent(MainActivity.this, AlerDialogpopup.class));
                        }
                    }
                }).start();


            }
        });
    }

    private int getvalue() {
        while (filesize < 100000)
        {
            filesize++;
            if(filesize == 10000)
            {
                return 10;
            }else if(filesize == 20000)
            {
                return 20;
            }else if(filesize == 30000)
            {
                return 40;
            }else if(filesize == 40000)
            {
                return 50;
            }

        }
        return 100;
    }
}