package com.example.multthreadingexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
Button buttonone,buttontwo;
Switch aSwitch;
    int count =0;
    private static final String TAG = "MainActivity";

    private Handler mainhandler = new Handler();
    HashMap<Integer,String> mymap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonone = findViewById(R.id.firstthreadstart);
        buttontwo = findViewById(R.id.secondthreadstart);
        aSwitch = findViewById(R.id.switchbtn);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(MainActivity.this, "visible", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "notvisible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <1)
                {
                    count = count+1;
                    ExampleThrad eone = new ExampleThrad();
                    SecondThrad sone  = new SecondThrad();
                    eone.start();
                    try {
                        eone.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(mymap);
                    sone.start();
                }


            }
        });
        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondThrad sone  = new SecondThrad();
                sone.start();
            }
        });
    }

    private void repeateforllopt() {
        for(int i =1;i<15;i++)
        {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "repeateforllopt: "+i );
        }
    }

    class ExampleThrad extends  Thread{
        @Override
        public void run() {
            super.run();

            for(int i =1;i<15;i++)
            {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "repeateforllopt: "+i );
                mymap.put(i,i+"");
            }
        }
    }

    class SecondThrad extends  Thread{
        @Override
        public void run() {
            super.run();

            for(int i =1;i<15;i++)
            {
                if( i== 5)
                {
                    mainhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttontwo.setText("UPdating ... ");
                        }
                    });

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "repeateforllopt: "+i+" secondthreadt" );
            }
        }
    }
}