package com.example.firebaseconcetpiexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firebaseconcetpiexample.utils.SharedPrefreferencconfig;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
EditText emailedit,passwordedti,numberedit,otpedit;
Button loginbtn,movesignupt,submitnumber,submintotp;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]

    SignInButton signInButton;
    TextView statuse,uid;
    ImageView profileimage;
    Button signout,disconnextbtn;
    private GoogleSignInClient mGoogleSignInClient;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
    String phonenumber;
    String verification_code;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectxml();
        handler = new Handler();
firebaseAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
movesignupt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }
});
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaail_text = emailedit.getText().toString();
                String password_text = passwordedti.getText().toString();

                if(emaail_text.equals("")  || emaail_text.equals(" ")){
                    emailedit.setError("Email required");
                }else if(password_text.equals("") || password_text.equals(" "))
                {
                    passwordedti.setError("Password required");
                }else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    loginuser(emaail_text,password_text);
                }
            }
        });

        submitnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_text = numberedit.getText().toString().trim();
                phonenumber = "+91"+number_text;



                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(firebaseAuth)
                                .setPhoneNumber(phonenumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(MainActivity.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbaake)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }

        });

        submintotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String otptext = otpedit.getText().toString();
                if(TextUtils.isEmpty(otptext))
                {
                    Toast.makeText(MainActivity.this, "please enter otp", Toast.LENGTH_SHORT).show();
                }else
                {
                    verficode(otptext);
                }
            }
        });
        if(getIntent() != null)
        {

        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                // Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            updateUI(firebaseAuth.getCurrentUser());
                        }
                    }
                });
            }
        });

        disconnextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                // Google revoke access
                mGoogleSignInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(firebaseAuth.getCurrentUser());
                    }
                });
            }
        });
    }

    private void verficode(String otptext) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code,otptext);
        singinwithcredention(credential);
    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbaake = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
      String code = phoneAuthCredential.getSmsCode();
verficode(code);
      //singinwithcredention(phoneAuthCredential);
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {

        Toast.makeText(MainActivity.this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        verification_code = s;
    }
};


    private void singinwithcredention(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(MainActivity.this, "Your phone number verified", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "error => "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginuser(String emaail_text, String password_text) {
firebaseAuth.signInWithEmailAndPassword(emaail_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Your Are LogedIn", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,HomeActivityy.class));
            finish();
        }
    }
})
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void connectxml() {
        emailedit = findViewById(R.id.emailayout);
        passwordedti = findViewById(R.id.passwordlayotlayout);
        loginbtn = findViewById(R.id.loginbt);
        progressBar = findViewById(R.id.loginprogressbaR);
        movesignupt = findViewById(R.id.movesignupu);
        submitnumber = findViewById(R.id.submitnumber);
        submintotp = findViewById(R.id.submitotp);
        numberedit = findViewById(R.id.numberlayout);
        otpedit = findViewById(R.id.otplayout);
        signInButton=findViewById(R.id.sign_in_button);
        statuse = findViewById(R.id.userstatus);
        uid = findViewById(R.id.userdetail);
        profileimage = findViewById(R.id.userprofiel);
        signout = findViewById(R.id.signoutbtn);
        disconnextbtn = findViewById(R.id.disconnextbtn);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//            updateUI(currentUser);
//        }


           Thread tone = new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(getIntent() != null  && getIntent().hasExtra("title")){

                                for(String key: getIntent().getExtras().keySet())
                                {
                                    Log.e(TAG, "onCreate: key :  "+key+" data is " + getIntent().getExtras().getString(key) );
                                    SharedPrefreferencconfig.savepassword(getApplicationContext(),key,getIntent().getExtras().getString(key));

                                }
                            }
                        }
                    });
                }
            });
           tone.start();
            try {
                tone.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           Intent intent = new Intent(MainActivity.this,HomeActivityy.class);
            intent.putExtra("title",SharedPrefreferencconfig.getUserPassword(getApplicationContext(),"title"));
            startActivity(new Intent(MainActivity.this,HomeActivityy.class));

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "error "+connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statuse.setText("useremail =>"+user.getEmail());
            uid.setText("userid =>"+user.getDisplayName());

            String url = user.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(url).into(profileimage);
            signInButton.setVisibility(View.GONE);
            signout.setVisibility(View.VISIBLE);
            disconnextbtn.setVisibility(View.VISIBLE);
        } else {
            statuse.setText("user is not signin");
            uid.setText("null");
            profileimage.setImageResource(R.drawable.ic_launcher_background);
            signInButton.setVisibility(View.VISIBLE);
            signout.setVisibility(View.GONE);
            disconnextbtn.setVisibility(View.GONE);
        }

    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                    }
                });
    }
}