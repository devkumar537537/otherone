package com.example.bindingexample.Serverlogic;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ServerLogics {
    boolean isLogin ;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "ServerLogics";
    public  boolean loginervermetho(String email,String password)
    {
         firebaseAuth = FirebaseAuth.getInstance();

        Log.e(TAG, "out of oncomplete "+isLogin );

        Thread tone = new Thread(runnable);
        tone.start();
        try {
            tone.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isLogin;
    }
private Runnable runnable = new Runnable() {
    @Override
    public void run() {

        firebaseAuth.signInWithEmailAndPassword("left@gmail.com","left123434").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    isLogin=true;
                    Log.e(TAG, " in onComplete: "+isLogin );

                }else
                {
                    isLogin =false;
                    Log.e(TAG, " error onComplete: "+task.getException() );
                }
            }

        });
    }
};

}
