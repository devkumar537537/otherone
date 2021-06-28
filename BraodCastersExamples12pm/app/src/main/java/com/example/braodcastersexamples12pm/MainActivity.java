package com.example.braodcastersexamples12pm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch wifiswitch;

    private WifiManager wifiManager;
   MyBroadCaster myBroadCaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiswitch = findViewById(R.id.wifiswitch);
     myBroadCaster = new MyBroadCaster();
        wifiManager =(WifiManager)   getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(Build.VERSION.SDK_INT> Build.VERSION_CODES.Q)
                    {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                        startActivityForResult(panelIntent,23);
                    }else  {
                        wifiManager.setWifiEnabled(true);
                        wifiswitch.setText("Wifi is One");
                }


                }else
                {
                    if(Build.VERSION.SDK_INT> Build.VERSION_CODES.Q)
                    {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                        startActivityForResult(panelIntent,23);
                    }else  {
                        wifiManager.setWifiEnabled(false);
                        wifiswitch.setText("Wifi is Off");
                    }


                }
            }
        });
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra)
            {
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiswitch.setChecked(true);
                    wifiswitch.setText("Wifi Is On");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiswitch.setChecked(false);
                    wifiswitch.setText("Wifi Is Off");
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter1.addAction(Intent.ACTION_TIMEZONE_CHANGED);

        registerReceiver(myBroadCaster,intentFilter1);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
         unregisterReceiver(myBroadCaster);
    }
}