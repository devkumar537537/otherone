package com.example.mymaterialdesingn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var extendedFloatingActionButton:ExtendedFloatingActionButton
    lateinit var emailedit:TextInputEditText
    lateinit var passwordedt:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectxml()

        extendedFloatingActionButton.setOnClickListener {view->
            var emailtext = emailedit.text.toString()
            var passwordtext = passwordedt.text.toString()

            Snackbar.make(view,"email => ${emailtext} \n and password $passwordtext",Snackbar.LENGTH_LONG)
                .setAction("Revers", View.OnClickListener {
                    Toast.makeText(applicationContext,"this is callback method",Toast.LENGTH_SHORT).show()
                })
                .show()
        }
    }

    private fun connectxml() {
        extendedFloatingActionButton = findViewById(R.id.floatingbtn)
        emailedit = findViewById(R.id.emailedit)
        passwordedt = findViewById(R.id.passordedit)
    }
}