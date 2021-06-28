package com.example.fingerprintscanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class FingerScannerHelperClass extends FingerprintManager.AuthenticationCallback {
    Context context;
    private CancellationSignal cancellationSignal;
    public FingerScannerHelperClass(Context context) {
        this.context = context;
    }
    public  void startauth(FingerprintManager manager,FingerprintManager.CryptoObject cryptoObject)
    {
        cancellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
//        manager.authenticate(cryptoObject,cancellationSignal,0,this,null);
        manager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        Toast.makeText(context, "Authentication error\n"+errString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {

        Toast.makeText(context, "You are not allowed to go further", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        Toast.makeText(context, "Authentication Help", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        Toast.makeText(context, "You succed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context,SecondActivity.class);
        context.startActivity(intent);

    }
}
