package com.example.myfirebasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var verfication_code: String

    lateinit var emaillayout:EditText
    lateinit var passwordlayout:EditText
    lateinit var loginbtn:Button
    lateinit var movetoregistet:Button
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var numberedit:EditText
    lateinit var otpedti:EditText
    lateinit var otpbtn:Button
    lateinit var numbetbtn:Button
    var phonenumber = "2342"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      connectxml()
        firebaseAuth = FirebaseAuth.getInstance()

        loginbtn.setOnClickListener {
            var email_text = emaillayout.text.toString().trim()
            var password_text = passwordlayout.text.toString().trim()

            if(email_text.isEmpty())
            {
                emaillayout.setError("Empty Email")
            }else if(password_text.isEmpty())
            {
                passwordlayout.setError("Empty Password")
            }else
            {
                logineuser(email_text,password_text)
            }
        }

        movetoregistet.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivituy::class.java))
        }

        numbetbtn.setOnClickListener {
            var numbertext = numberedit.text.toString().trim()
            phonenumber = "+91${numbertext}"

            var options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phonenumber)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this@MainActivity)
                .setCallbacks(mCallback)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
        otpbtn.setOnClickListener {
            var otptext = otpedti.text.toString().trim()
            verifyecode(otptext)
        }
    }
    private var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(phoneauthcredial: PhoneAuthCredential) {
         var code = phoneauthcredial.smsCode
            verifyecode(code)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
        Toast.makeText(applicationContext,"error => ${p0.message}",Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verfication_codee: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verfication_codee, p1)
            verfication_code = verfication_codee
        }

    }

    private fun verifyecode(code: String?) {
   var credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verfication_code,code!!)
        signinwithcrediential(credential)
    }

    private fun signinwithcrediential(credential: PhoneAuthCredential) {
       firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this@MainActivity,object :OnCompleteListener<AuthResult>{
           override fun onComplete(task: Task<AuthResult>) {
              if(task.isSuccessful)
              {
                  Toast.makeText(applicationContext,"NumberVerified",Toast.LENGTH_SHORT).show()
              }else
              {
                  Toast.makeText(applicationContext,"error "+task.exception,Toast.LENGTH_SHORT).show()
              }
           }


       })
    }

    private fun logineuser(emailText: String, passwordText: String) {

        firebaseAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener { task ->
            if(task.isSuccessful)
            {
                val intent = Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)

            }else
            {
                Toast.makeText(applicationContext,"error  "+task.exception,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connectxml()
 {
     emaillayout = findViewById(R.id.emailayout)
     passwordlayout = findViewById(R.id.passworldlayout)
     loginbtn = findViewById(R.id.loginbtn)
     movetoregistet = findViewById(R.id.registerbtn)
     numberedit = findViewById(R.id.numberedit)
     otpedti = findViewById(R.id.otpedittext)
     numbetbtn = findViewById(R.id.submitnumbetbtn)
     otpbtn = findViewById(R.id.submitotp)
 }

    override fun onStart() {
        super.onStart()
//        if(firebaseAuth.currentUser != null)
//        {
//            var intent = Intent(this@MainActivity,HomeActivity::class.java)
//            startActivity(intent)
//        }
    }
}