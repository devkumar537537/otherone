package com.example.googlespreadsheetsample

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

class ShowUserDetail : AppCompatActivity() {
   lateinit var editname: EditText
    lateinit var editnumber: EditText
    lateinit var editid: EditText
    lateinit var editdate: EditText
    lateinit var updatebtn: Button
    lateinit var deletebtn: Button

    var number_text: String? = " "
     var email_text: String? = " "
     var id_text: String? = " "
     var date_text: String? = " "

    lateinit  var detailProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user_detail)
        bindviews()
        getdata()
        updatviews()
        updatebtn!!.setOnClickListener {
            detailProgressBar!!.visibility = View.VISIBLE
            updatedata()
        }
        deletebtn!!.setOnClickListener {
            detailProgressBar!!.visibility = View.VISIBLE
            deleteitem()
        }
    }

    private fun deleteitem() {
        id_text = editid!!.text.toString()
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbz390oSR2iEQWgyZ0JgyD33ikiPEDEMBKj1tpAKMFeW8YToOCeWrmkUW3ZS6mp3SQDL/exec?action=delete&id=$id_text",
            { response ->
                detailProgressBar!!.visibility = View.GONE
                Toast.makeText(this@ShowUserDetail, response, Toast.LENGTH_SHORT).show()
            }) { error ->
            detailProgressBar!!.visibility = View.GONE
            Toast.makeText(this@ShowUserDetail, error.message, Toast.LENGTH_SHORT).show()
        }
        val soketTimeout = 50000
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(soketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        val requestQueue = Volley.newRequestQueue(this@ShowUserDetail)
        requestQueue.add(stringRequest)
    }

    private fun updatedata() {
        email_text = editname!!.text.toString()
        number_text = editnumber!!.text.toString()
        id_text = editid!!.text.toString()
        date_text = editdate!!.text.toString()
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            "https://script.google.com/macros/s/AKfycbz390oSR2iEQWgyZ0JgyD33ikiPEDEMBKj1tpAKMFeW8YToOCeWrmkUW3ZS6mp3SQDL/exec",
            Response.Listener { response ->
                detailProgressBar!!.visibility = View.GONE
                Toast.makeText(this@ShowUserDetail, response, Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                detailProgressBar!!.visibility = View.GONE
                Toast.makeText(this@ShowUserDetail, error.message, Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["action"] = "update"
                params["email"] = email_text!!
                params["number"] = number_text!!
                params["id"] = id_text!!
                params["date"] = date_text!!
                return params
            }
        }
        val soketTimeout = 50000
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(soketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        val requestQueue = Volley.newRequestQueue(this@ShowUserDetail)
        requestQueue.add(stringRequest)
    }

    private fun updatviews() {
        editname!!.setText(email_text)
        editnumber!!.setText(number_text)
        editid!!.setText(id_text)
        editdate!!.setText(date_text)
    }

    private fun getdata() {
        if (intent != null) {
            email_text = intent.getStringExtra("email")
            email_text = intent.getStringExtra("email")
            number_text = intent.getStringExtra("number")
            id_text = intent.getStringExtra("id")
            date_text = intent.getStringExtra("date")
        }
    }

    private fun bindviews() {
        editname = findViewById(R.id.updateemail)
        editnumber = findViewById(R.id.update_user_number)
        detailProgressBar = findViewById(R.id.detail_progressbar)
        updatebtn = findViewById(R.id.update_btn)
        deletebtn = findViewById(R.id.delete_btn)
        editid = findViewById(R.id.update_user_id)
        editdate = findViewById(R.id.update_user_date)
    }
}