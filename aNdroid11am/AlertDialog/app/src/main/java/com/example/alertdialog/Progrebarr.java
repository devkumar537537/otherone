package com.example.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

public class Progrebarr extends AppCompatActivity {
Button button,showdatpickbtn;
TextView showdateview;
TimePicker timePicker;
ProgressDialog progressDialog;
private int progresstatus =0;
private Handler progresbarhandle =new Handler();
private long filesize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progrebarr);
timePicker = findViewById(R.id.timepicker);
showdatpickbtn = findViewById(R.id.timepickerbtn);
showdateview = findViewById(R.id.datetextview);
        addlisteneronButtonClick();

        showdateview.setText(getdate());
        showdatpickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdateview.setText(getdate());
            }
        });

    }

    private void addlisteneronButtonClick() {
        button = findViewById(R.id.downloadbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("File Downloading....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progresstatus = 0;
                filesize = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progresstatus< 100)
                        {
                            progresstatus = dooperation();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progresbarhandle.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progresstatus);
                                }
                            });
                        }
                        if(progresstatus >= 100)
                        {
                            try {
                                Thread.sleep(1000);
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

    private int dooperation() {
        while (filesize <= 10000)
        {
            filesize++;
            if(filesize == 1000)
            {
                return 10;
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
                return 60;
            }else if(filesize == 7000)
            {
                return 70;
            }else if(filesize == 8000)
            {
                return 80;
            }else if(filesize == 9000){
                return 90;
            }
        }
        return 100;
    }
    private String getdate()
    {
        String time = timePicker.getHour()+" : "+timePicker.getMinute();
        return time;
    }
}