package com.example.adnroidspreatsheetkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

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
shomovebtn.setOnClickListener {
    startActivity(Intent(applicationContext,Studentlist::class.java))
}
adddatabtn.setOnClickListener {
    val name_text = nameEdit!!.text.toString().trim()
    val numbet_text = numberEdit!!.text.toString().trim()
    progressBar!!.visibility = View.VISIBLE
    insertdata(name_text,numbet_text)
}
    }

    private fun insertdata(nameText: String, numbetText: String) {

        var stringRequest : StringRequest = object :StringRequest(
           Method.POST,
            "https://script.google.com/macros/s/AKfycbyJoTak287ulglz0pxoj6LO0HMO_ezWwCKcREF_p7XGNZw_RH5mU37TIOEsKjEEdx9i/exec"
            ,
            Response.Listener { response ->
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity,"error ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }){
            override fun getParams(): MutableMap<String, String> {

                val params: MutableMap<String, String> = HashMap()
                params["action"] = "addItem"
                params["name"] = nameText
                params["number"] = numbetText
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