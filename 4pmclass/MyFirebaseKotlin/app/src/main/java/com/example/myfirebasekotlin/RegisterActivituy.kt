package com.example.myfirebasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivituy : AppCompatActivity() {
    lateinit var emaillayout: EditText
    lateinit var passwordlayout: EditText
    lateinit var numberlayout:EditText
    lateinit var namelayout:EditText
    lateinit var register: Button
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_activituy)
        connectxml()
        firebaseAuth = FirebaseAuth.getInstance()
        
        register.setOnClickListener { 
            var email_text = emaillayout.text.toString().trim()
            var passwort_Tesxt = passwordlayout.text.toString().trim()
            var number_text = numberlayout.text.toString().trim()
            var nametext = namelayout.text.toString().trim()
            
            registeusert(email_text,passwort_Tesxt,number_text,nametext)
        }
    }

    private fun registeusert(emailText: String, passwortTesxt: String, numberText: String, nametext: String) {
            firebaseAuth.createUserWithEmailAndPassword(emailText,passwortTesxt).addOnCompleteListener {task ->
                if(task.isSuccessful)
                {
                    var databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData")
                    var userid = firebaseAuth.currentUser?.uid

                    var hashMap = hashMapOf(
                        "userid" to userid,
                        "email" to emailText,
                        "password" to passwortTesxt,
                        "number" to numberText,
                        "name" to nametext,
                        "imageurl" to "default"
                    )
                    databaseReference.child(userid!!).setValue(hashMap).addOnCompleteListener {task ->
                        if(task.isSuccessful)
                        {
                            Toast.makeText(applicationContext,"Complete",Toast.LENGTH_SHORT).show()
                            var intent = Intent(this@RegisterActivituy,HomeActivity::class.java)
                            finish()
                        }else
                        {
                            Toast.makeText(applicationContext,"error "+task.exception,Toast.LENGTH_SHORT).show()
                        }
                    }
                }else
                {
                     Toast.makeText(applicationContext,"error "+task.exception,Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun connectxml() {
        emaillayout = findViewById(R.id.emailayoutregister)
        passwordlayout = findViewById(R.id.passworldlayoutregitsert)
        numberlayout = findViewById(R.id.numberayoutregister)
        namelayout = findViewById(R.id.namelayoutregitsert)
        register = findViewById(R.id.Submit)
    }
}