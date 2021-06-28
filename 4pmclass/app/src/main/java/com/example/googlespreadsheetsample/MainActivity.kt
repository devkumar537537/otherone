package com.example.googlespreadsheetsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class MainActivity : AppCompatActivity() {
   lateinit var nameEdit: EditText
   lateinit var numberEdit: EditText
    lateinit var adddatabtn: Button
    lateinit var shomovebtn: Button
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindxml()
        adddatabtn!!.setOnClickListener {
            val name_text = nameEdit!!.text.toString().trim { it <= ' ' }
            val numbet_text = numberEdit!!.text.toString().trim { it <= ' ' }
            progressBar!!.visibility = View.VISIBLE
            insertdata(name_text,numbet_text)
        }
        shomovebtn!!.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Userlist::class.java
                )
            )
        }
    }

    private fun insertdata(name_text: String, numbet_text: String) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            "https://script.google.com/macros/s/AKfycbz390oSR2iEQWgyZ0JgyD33ikiPEDEMBKj1tpAKMFeW8YToOCeWrmkUW3ZS6mp3SQDL/exec",
            Response.Listener { response ->
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "erroro " + error.message, Toast.LENGTH_SHORT)
                    .show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["action"] = "addItem"
                params["email"] = name_text
                params["number"] = numbet_text
                return params
            }
        }
        val soketTimeout = 50000
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(soketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        requestQueue.add(stringRequest)
    }

    private fun bindxml() {
        nameEdit = findViewById(R.id.name)
        numberEdit = findViewById(R.id.number)
        adddatabtn = findViewById(R.id.insertdata)
        progressBar = findViewById(R.id.progress_insert)
        shomovebtn = findViewById(R.id.move_to_detaillist)
    }
}