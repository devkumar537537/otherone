package com.example.myfirebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirebaseapplication.practiceexammp.LogInActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class LogInActivityStart extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
EditText emaileedit,passwordedit,numberedit,otpedit;
Button butlogin,submintnumber,otpsubmit;
FirebaseAuth firebaseAuth;
String phonenumber;
String verification_code;
//googlesignin
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    SignInButton signInButton;
    TextView statuse,uid;
    ImageView profileimage;
    Button signout,disconnextbtn,facebooksigninmove;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstart);
        firebaseAuth = FirebaseAuth.getInstance();
        connectxml();





        butlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext  =emaileedit.getText().toString().trim();
                String password_text = passwordedit.getText().toString().trim();

                if(TextUtils.isEmpty(emailtext))
                {
                    Toast.makeText(LogInActivityStart.this, "empty email", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password_text))
                {
                    Toast.makeText(LogInActivityStart.this, "password empty", Toast.LENGTH_SHORT).show();
                }else
                {
                    loginuser(emailtext,password_text);
                }
            }
        });
        submintnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String number_text = numberedit.getText().toString().trim();
               phonenumber = "+91"+number_text;
            startPhoneNumberVerification(phonenumber);
                }

        });
        otpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opt_text = otpedit.getText().toString().trim();

                if(TextUtils.isEmpty(opt_text))
                {
                    Toast.makeText(LogInActivityStart.this, "please enter otp", Toast.LENGTH_SHORT).show();
                }else
                {
                    verficode(opt_text);
                }
            }
        });
    }

    private void verficode(String opt_text) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code,opt_text);
        singinwithcredention(credential);
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbaake)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]


    }
    private void singinwithcredention(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(LogInActivityStart.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LogInActivityStart.this, "Otp Verified", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(LogInActivityStart.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbaake = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        String code = phoneAuthCredential.getSmsCode();
        verficode(code);
       // singinwithcredention(phoneAuthCredential);
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        Toast.makeText(LogInActivityStart.this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        verification_code = s;
    }
};

    private void loginuser(String emailtext, String password_text) {

        firebaseAuth.signInWithEmailAndPassword(emailtext,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LogInActivityStart.this, "Your loged In", Toast.LENGTH_SHORT).show();

                }else
                {

                    startActivity(new Intent(LogInActivityStart.this,CreateAccount.class));

                }

            }
        });
    }

    private void connectxml() {
        emaileedit = findViewById(R.id.email);
        passwordedit = findViewById(R.id.password);
        butlogin = findViewById(R.id.loginbtn);
        otpedit = findViewById(R.id.otpeditext);
        numberedit = findViewById(R.id.usernumber);
        submintnumber = findViewById(R.id.submintnumber);
        otpsubmit = findViewById(R.id.submitotp);
        signInButton=findViewById(R.id.sign_in_button);
        statuse = findViewById(R.id.userstatus);
        uid = findViewById(R.id.userdetail);
        profileimage = findViewById(R.id.userprofiel);
        signout = findViewById(R.id.signoutbtn);
        disconnextbtn = findViewById(R.id.disconnextbtn);
        facebooksigninmove = findViewById(R.id.movetofacebooksignin);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}