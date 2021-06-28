package com.example.componentkotlin

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var builder:AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         builder = AlertDialog.Builder(this)
        var bton = findViewById<Button>(R.id.popbutn)
        var builder = AlertDialog.Builder(this)
        builder.setMessage("Are Yor Sure to Go Activity ?")
        builder.setIcon(R.drawable.warning_plate)
        builder.setTitle("Moving Warning")
        builder.setPositiveButton("Yes"){dialog, which ->

            val intent = Intent(applicationContext,SecondActivity::class.java)
            startActivity(intent)

        }
        builder.setNegativeButton("No"){dialog, which ->
            Toast.makeText(applicationContext,"You choose No",Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancel"){dialog, which ->
            Toast.makeText(applicationContext,"You choose cancel",Toast.LENGTH_SHORT).show()

        }

        val alertdialogn:AlertDialog = builder.create()
        alertdialogn.setCancelable(false)
        bton.setOnClickListener {

            alertdialogn.show()

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("Are Yor Sure to Go Activity ?")
            builder.setIcon(R.drawable.warning_plate)
            builder.setTitle("Moving Warning")
            builder.setPositiveButton("Yes"){dialog, which ->

             finish()

            }
            builder.setNegativeButton("No"){dialog, which ->
                val intent = Intent(applicationContext,SecondActivity::class.java)
                startActivity(intent)
            }

            builder.setNeutralButton("Cancel"){dialog, which ->
                Toast.makeText(applicationContext,"You choose cancel",Toast.LENGTH_SHORT).show()

            }

            val alertdialogn:AlertDialog = builder.create()
            alertdialogn.setCancelable(false)
            alertdialogn.show()
            return true
        }
        return false

    }
}