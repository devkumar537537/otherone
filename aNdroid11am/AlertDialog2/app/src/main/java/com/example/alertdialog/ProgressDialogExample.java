package com.example.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProgressDialogExample extends AppCompatActivity {
Button button,getvaluebtn;
ProgressDialog progressDialog;
Handler progresshandler = new Handler();
int progresstatus = 0;
int filesize = 0;
RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog_example);
        button= findViewById(R.id.popup_proggress_dialog);
        radioGroup = findViewById(R.id.readio_group);

        getvaluebtn = findViewById(R.id.RightAfter);
        getvaluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
               RadioButton radioButton = findViewById(id);
             
                if(id == -1)
                {
                    Toast.makeText(ProgressDialogExample.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
                }else
                {
                    String text = radioButton.getText().toString();
                    Toast.makeText(ProgressDialogExample.this, "select "+radioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
       showprogresdialog();       showprogresdialog();
    }

    private void showprogresdialog() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("File is Downloading....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.show();

                progresstatus =0;
                filesize = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progresstatus< 100)
                        {
                            progresstatus = getvalues();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progresshandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progresstatus);
                                }
                            });
                        }
                        if(progresstatus >= 100)
                        {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();

                        }
                    }
                }).start();
            }
        });
    }

    private int getvalues() {
        while (filesize < 10000)
        {
            filesize++;
            if(filesize == 1000)
            {
                return  10;
            }else if(filesize == 2000)
            {
                return 20;
            }else if(filesize == 3000)
            {
                return 30;
            }else if(filesize == 4000)
            {
                return 40;
            }else if(filesize == 5000)
            {
                return 50;
            }else if(filesize == 6000)
            {
                return 70;
            }
        }
        return 100;

    }
}