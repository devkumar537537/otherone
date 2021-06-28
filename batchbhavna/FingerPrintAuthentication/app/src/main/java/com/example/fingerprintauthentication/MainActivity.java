package com.example.fingerprintauthentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_NAme = "anything";
    private TextView textView;
    String[] permissions = {Manifest.permission.USE_FINGERPRINT};
    int permsiisoncode = 123;
    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewfirst);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.USE_FINGERPRINT ) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,permsiisoncode);
            }
        }
        if (!fingerprintManager.isHardwareDetected()){
            textView.setText("Your Device does not support finger print");
        }

    

        if(!fingerprintManager.hasEnrolledFingerprints())
        {
            textView.setText("No FingerPrint configured.Please register at least one firngerprint");
        }

        if (!keyguardManager.isKeyguardSecure()) {
            textView.setText("Please enable lockscreen lock");
        } else {
            try {
                generateKeys();
            } catch (FingerPringException fingerPringExcetpion) {
                fingerPringExcetpion.printStackTrace();
            }


        }
        if (initChiper()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerPrintHelperClass helpterClass = new FingerPrintHelperClass(this);
            helpterClass.startauth(fingerprintManager,cryptoObject);

        }
    }

    private boolean initChiper() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |NoSuchPaddingException e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to get Cipher",e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAme,null);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            return true;
        }catch (KeyPermanentlyInvalidatedException e)
        {
            return  false;
        } catch (CertificateException | IOException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed t int ciperh",e);
        }


    }

    private void generateKeys() throws FingerPringException {

        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAme,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());

                    keyGenerator.generateKey();


        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException
                | IOException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            throw new FingerPringException("finger invalide");
        }
        
    }

    class FingerPringException extends  Exception{
        public FingerPringException(String message) {
            super(message);
        }
    }
}