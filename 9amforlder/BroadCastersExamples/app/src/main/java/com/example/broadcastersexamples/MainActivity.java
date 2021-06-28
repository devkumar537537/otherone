package com.example.broadcastersexamples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {
    private SwitchMaterial wifiSwitch;
   WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiSwitch = findViewById(R.id.wifi_switch);
        boolean resutl = wifiSwitch.isChecked();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            {
                Intent intent = new Intent(Settings.Panel.ACTION_WIFI);
                startActivityForResult(intent,0);
            }else
            {
                wifiManager.setWifiEnabled(true);
            }

            wifiSwitch.setText("Wifi is On");
        }else
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            {
                Intent intent = new Intent(Settings.Panel.ACTION_WIFI);
                startActivityForResult(intent,0);
            }else
            {
                wifiManager.setWifiEnabled(false);
            }

            wifiSwitch.setText("Wifi is Off");
        }
    }
});
  wifiSwitch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
if(wifiSwitch.isChecked())
{
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
    {
        Intent intent = new Intent(Settings.Panel.ACTION_WIFI);
        startActivityForResult(intent,0);
    }else
    {
        wifiManager.setWifiEnabled(false);
    }

}else
{

}
      }
  });
    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }
    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("WiFi is ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("WiFi is OFF");
                    break;
            }
        }
    };

    public void toggleWiFi(boolean status){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (status && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (!status && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
}