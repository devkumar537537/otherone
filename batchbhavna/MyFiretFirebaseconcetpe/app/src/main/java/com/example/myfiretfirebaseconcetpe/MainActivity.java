package com.example.myfiretfirebaseconcetpe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.facebook.appevents.UserDataStore.EMAIL;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
EditText emailedit,passswordedit,numberedit,otpedit;
TextView notficationview;
Button loginbtn,movetologin,numberbtn,otpbtn;
FirebaseAuth firebaseAuth;
String phonenumber;
    String verification_code;



    //connectio view for googlesignIn
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 456;
    private GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    TextView statuse,uid;
    ImageView profileimage,profileimage2;
    Button signout,disconnextbtn,facebooksigninmove;
//facebook login btn
    LoginButton loginButton;
    CallbackManager callbackManager;


    TextView email,name,id;
    Button signoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        combinview();

        //initial googleclient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");
        loginButton =   findViewById(R.id.login_button);

        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handfacboologinwithfirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG, "facebook:onCancel");

                updateUIsecond(null);
            }

            @Override
            public void onError(FacebookException exception) {
                // App code

                Log.d(TAG, "facebook:onError"+ exception);

                updateUIsecond(null);
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
      movetologin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(MainActivity.this,RegisterUser.class));
          }
      });
        firebaseAuth = FirebaseAuth.getInstance();
notficationview = findViewById(R.id.noftifcationinfo);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= "sometig";
                String passwor = "sdfsjdf";
              String emailt_text = emailedit.getText().toString();
              String password_text = passswordedit.getText().toString();

              if(emailt_text.isEmpty())
              {
                emailedit.setError("Enter Eemail");
                return;
              }else if(password_text.isEmpty())
                {
                    passswordedit.setError("password");
                    return;
                }else
              {
                  loginuser(emailt_text,password_text);
              }
            }
        });
        numberbtn.setOnClickListener(new View.OnClickListener() {
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
otpbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String otptext = otpedit.getText().toString().trim();

        verficode(otptext);
    }
});
if(getIntent() != null && getIntent().hasExtra("title"))
{
StringBuilder stringBuilder = new StringBuilder();
    for(String key : getIntent().getExtras().keySet())
    {
        Log.e(TAG, "onCreate: key :  "+key+" data is " + getIntent().getExtras().getString(key) );
        stringBuilder.append(getIntent().getExtras().getString(key)+"\n");

    }
    notficationview.setText(stringBuilder.toString());

}
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                LoginManager.getInstance().logOut();

                updateUIsecond(null);
            }
        });
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful())
                {
                    String token = task.getResult().getToken();
                    Log.d("Token","OnComplete : => "+token);
                    notficationview.append(token);
                }else
                {
                    Toast.makeText(MainActivity.this, "exception"+task.getException() , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateUIsecond(FirebaseUser user) {


            if (user != null) {
                name.setText(user.getDisplayName());
                email.setText(user.getEmail());
                id.setText(user.getUid());

                String imageure = user.getPhotoUrl().toString();
                if(user.getPhotoUrl().toString() == null)
                {
                    Toast.makeText(this, "There is no url in image", Toast.LENGTH_SHORT).show();
                }else
                {
                    Glide.with(getApplicationContext()).load(imageure).into(profileimage);
                }



                loginButton.setVisibility(View.GONE);
                signoutbtn.setVisibility(View.VISIBLE);
            } else {


                loginButton.setVisibility(View.VISIBLE);
                signoutbtn.setVisibility(View.GONE);
            }

    }

    private void handfacboologinwithfirebase(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user =  firebaseAuth.getCurrentUser();
                            updateUIsecond(user);
                        } else {

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUIsecond(null);
                        }


                    }
                });

    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbaake = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
          verficode(code);
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

    private void verficode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code,code);
        singinwithcredention(credential);
    }

    private void singinwithcredention(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "NumberVerified", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "error "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void combinview() {
        emailedit = findViewById(R.id.emailedit);
        passswordedit = findViewById(R.id.passwordedit);
        loginbtn = findViewById(R.id.logintbtn);
        movetologin = findViewById(R.id.movetologin);
        numberbtn = findViewById(R.id.submitnumber);
        otpbtn = findViewById(R.id.submitotp);
        numberedit = findViewById(R.id.enternumber);
        otpedit = findViewById(R.id.otpedti);
        signInButton=findViewById(R.id.sign_in_button);
        statuse = findViewById(R.id.userstatus);
        uid = findViewById(R.id.userdetail);
        profileimage = findViewById(R.id.userprofiel);
        signout = findViewById(R.id.signoutbtn);
        disconnextbtn = findViewById(R.id.disconnextbtn);
        facebooksigninmove = findViewById(R.id.movetofacebooksignin);
        loginButton = findViewById(R.id.login_button);
        profileimage = findViewById(R.id.profileImage);
        email =findViewById(R.id.email);
        name = findViewById(R.id.name);
        id = findViewById(R.id.userId);
        signoutbtn = findViewById(R.id.facesignout);
    }


    private void loginuser(String emailt_text, String password_text) {

        firebaseAuth.signInWithEmailAndPassword(emailt_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Your logged in Success fully", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(MainActivity.this,HomeActivity.class));
                  finish();
                }else
                {
                    Toast.makeText(MainActivity.this, "eroor "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            startActivity(new Intent(MainActivity.this,HomeActivity.class));
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
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
        }else
        {
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
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                }else
                {
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(MainActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
}