package com.example.firebaseconcepts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class LogInActivity extends AppCompatActivity {
EditText emailedit,passwordedit,numberedit,otpedit;
Button login,numbersubmintbtn,otpsubmintbtn;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions googleSignInOptions;
    int GOOGLE_SIGN_IN_CODE = 123;

    LoginButton loginButton;
    CallbackManager callbackManager;


    String verification_code;
    String phnenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_login);
        bindview();
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        signInButton = findViewById(R.id.google_sign_inbtn);


        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singInGoogle();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email_text = emailedit.getText().toString().trim();
                String password_text = passwordedit.getText().toString().trim();

                loginuser(email_text,password_text);
            }
        });
        numbersubmintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numbertext = numberedit.getText().toString().trim();
                phnenumber = "+91"+numbertext;
                if(TextUtils.isEmpty(numbertext))
                {
                    Toast.makeText(getApplicationContext(),"please enter numbert",Toast.LENGTH_LONG).show();
                }else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phnenumber,
                            60,
                            TimeUnit.SECONDS,
                             LogInActivity.this,
                            mCallbaake
                    );
                }
            }
        });
        otpsubmintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_text = otpedit.getText().toString().trim();
                if(TextUtils.isEmpty(otp_text))
                {
                    Toast.makeText(LogInActivity.this, "please provide otp", Toast.LENGTH_SHORT).show();
                }else{

                    verfiycode(otp_text);
                }
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;


                Log.e("status",loginResult.getAccessToken().toString());
                handlefacboologinwithfirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LogInActivity.this, "face book signin cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LogInActivity.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void singInGoogle() {

        Intent sigininginten = googleSignInClient.getSignInIntent();
        startActivityForResult(sigininginten,GOOGLE_SIGN_IN_CODE);
    }

    private void handlefacboologinwithfirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LogInActivity.this, "Your are loged in", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(LogInActivity.this, "You are not verified", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbaake = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            signinWithNumbercreditenls(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(LogInActivity.this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verification_code = s;
        }
    };

    private void verfiycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code,code);
        signinWithNumbercreditenls(credential);
    }

    private void signinWithNumbercreditenls(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(LogInActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LogInActivity.this, "Your number verified", Toast.LENGTH_SHORT).show();
                }else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LogInActivity.this, "error "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginuser(String email_text, String password_text) {
        firebaseAuth.signInWithEmailAndPassword(email_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(LogInActivity.this,HomeActivitiy.class));
                    Toast.makeText(LogInActivity.this, "You are verified", Toast.LENGTH_SHORT).show();
                }else
                {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(LogInActivity.this,RegisterActivity.class));
                    Toast.makeText(LogInActivity.this, "Please register your self", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindview() {
        emailedit = findViewById(R.id.useremail);
        passwordedit=findViewById(R.id.userpassword);
        login = findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progrebasebar);
        otpedit = findViewById(R.id.otpeditext);
        numberedit = findViewById(R.id.usernumber);
        numbersubmintbtn = findViewById(R.id.submintnumber);
        otpsubmintbtn = findViewById(R.id.submitotp);
        loginButton = findViewById(R.id.facebooklogin_button);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser != null)
//        {
//            startActivity(new Intent(LogInActivity.this,HomeActivitiy.class));
//            finish();
//        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_SIGN_IN_CODE)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handletask(task);
        }
    }

    private void handletask(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);

            firebaseauthewithgoogleclient(googleSignInAccount.getIdToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void firebaseauthewithgoogleclient(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(LogInActivity.this, "Your are signin with email", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(LogInActivity.this, "erroro"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
