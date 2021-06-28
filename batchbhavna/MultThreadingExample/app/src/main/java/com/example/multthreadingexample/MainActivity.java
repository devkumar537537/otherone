package com.example.multthreadingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText textedit;
Button startthread,showtoastbtn;
private Handler updatehnadler;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textedit = findViewById(R.id.infoedit);
        startthread = findViewById(R.id.startfirstthread);
        updatehnadler = new Handler();
        startthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FirstProcess fone = new FirstProcess();
              fone.start();
              SecondProcess sone = new SecondProcess();
              sone.start();
            }
        });
        showtoastbtn=findViewById(R.id.showtaost);

        showtoastbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textedit.getText().toString();
                Toast.makeText(MainActivity.this, "yes working "+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class FirstProcess extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i = 0;i<=10;i++)
            {
                int j = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updatehnadler.post(new Runnable() {
                    @Override
                    public void run() {
                        if( j == 10)
                        {
                            startthread.setText("Process End");
                            Toast.makeText(MainActivity.this, "Process complete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Log.e(TAG, "first process  "+i );
            }
        }
    }

    class SecondProcess extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i = 0;i<=10;i++)
            {
                int j = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updatehnadler.post(new Runnable() {
                    @Override
                    public void run() {
                        if( j == 10)
                        {
                            startthread.setText("Process End");
                            Toast.makeText(MainActivity.this, "SecondProcess complete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Log.e(TAG, "Second process  "+i );
            }
        }
    }
}