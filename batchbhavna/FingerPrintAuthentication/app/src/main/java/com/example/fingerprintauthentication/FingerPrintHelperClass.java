package com.example.fingerprintauthentication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class FingerPrintHelperClass extends FingerprintManager.AuthenticationCallback  {
    Context context;
    private CancellationSignal cancellationSignal;
    public FingerPrintHelperClass(Context context) {
        this.context = context;
    }

    public  void startauth(FingerprintManager manager,FingerprintManager.CryptoObject cryptoObject)
    {
        cancellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        manager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }
    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "Nofinger fournd", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        Toast.makeText(context, "You succed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        Toast.makeText(context, "eroro  "+errString, Toast.LENGTH_SHORT).show();
    }
}

