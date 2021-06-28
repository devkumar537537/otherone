package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {
Button firstbtn,secondbtn;
Handler handler;
    int i =0;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstbtn = findViewById(R.id.startthread);
        secondbtn = findViewById(R.id.startsecond);

        handler = new Handler();
        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstbtn.setText("Right");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                          for( i =0;i<= 8;i++)
                          {
                              try {
                                  Thread.sleep(1000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              Log.e(TAG, "process first "+i );
                              handler.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      if(i == 5)
                                      {
                                          Snackbar.make(v,"This is value",Snackbar.LENGTH_SHORT)
                                                  .setAction("UNOD", new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                      }
                                                  }).show();
                                      }
                                  }
                              });

                          }
                    }
                }).start();
//                 FirstProcess fone = new FirstProcess();
//                 SecondProcess sone = new SecondProcess();
//                 fone.start();
////                try {
////                    fone.join();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                sone.start();
            }
        });
    }

    class FirstProcess extends Thread{
        int i;
        @Override
        public void run() {
            super.run();
            for( i =0;i<=10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(i == 3)
                        {
                            firstbtn.setText("Executing");
                        }

                        if(i == 9)
                        {
                            firstbtn.setText("Ending..");
                        }
                    }
                });

                Log.e(TAG, "firstProcess "+i );

            }
            i = 0;
           firstbtn.setText("Right");

        }
    }
    class SecondProcess extends Thread{
        int i;
        @Override
        public void run() {
            super.run();
            for( i =0;i<=10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      if(i == 8)
                      {
                          startActivity(new Intent(MainActivity.this,SecondActivity.class));
                      }
                    }
                });

                Log.e(TAG, "firstProcess "+i );

            }
            i = 0;


        }
    }
}