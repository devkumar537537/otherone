package com.example.menusinkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var movetobnt:Button
    lateinit var showsnackbar:Button
    lateinit var emailedit:EditText
    lateinit var passwordedit:EditText
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movetobnt = findViewById(R.id.movetotsecon);

        movetobnt.setOnClickListener {
            startActivity(Intent(applicationContext,SecondActivity::class.java))
        }

        showsnackbar = findViewById(R.id.showsnackbar)
        emailedit = findViewById(R.id.emailayout)
        passwordedit = findViewById(R.id.passwordlayout)

        showsnackbar.setOnClickListener { view->

           var emailt_text =  emailedit.text.toString()
            var password_text = passwordedit.text.toString()

            Snackbar.make(view,"email $emailt_text \n password => $password_text",Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
            //                        Toast.makeText(applicationContext,"This is revers action",Toast.LENGTH_SHORT).show()
                        var toast = Toast(applicationContext)

                        var inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        var view = inflater.inflate(R.layout.customtoast,null)

                        toast.view = view
                        toast.setGravity(Gravity.CENTER_VERTICAL,200,200)
                        toast.duration = Toast.LENGTH_LONG
                      toast.show()


                    }.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuiflater : MenuInflater = menuInflater
        menuiflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if(id == R.id.firstOption)
        {
            Toast.makeText(applicationContext,"selected ${item.title} ",Toast.LENGTH_SHORT).show()
        }else if(id == R.id.SecondOption)
        {

        }else if(id == R.id.submenufirst)
        {
            Toast.makeText(applicationContext,"onclick ${item.title}",Toast.LENGTH_SHORT).show()
        }else if(id == R.id.submentsecond)
        {
            Toast.makeText(applicationContext,"onclick ${item.title}",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}