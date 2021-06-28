package com.example.spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Progressbarrexampel extends AppCompatActivity {
Button  openprogressbar;

    ProgressDialog progressDialog;
   private Handler progresshandler = new Handler();
    int progresstatus = 0;
    int filesize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbarrexampel);

        openprogressbar = findViewById(R.id.showprogrebar);

        openprogressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showprogresdialog(v);
            }
        });
    }

    private void showprogresdialog(View v) {






        progressDialog = new ProgressDialog(v.getContext());
        progressDialog.setMessage("File is Downloading....");
       progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
       progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
       progressDialog.show();

               progresstatus =0;
       filesize = 0;


       new Thread(new Runnable() {
           @Override
           public void run() {
               while (progresstatus< 100)
               {
                   progresstatus = getvalues();//10
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

                   if(progresstatus >= 100)
                   {
                       try {
                           Thread.sleep(2000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       progressDialog.setMessage("Download Completed");
                       try {
                           Thread.sleep(3000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                      progressDialog.dismiss();


                   }
               }
           }
       }).start();


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