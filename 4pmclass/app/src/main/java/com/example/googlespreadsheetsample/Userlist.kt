package com.example.googlespreadsheetsample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class Userlist : AppCompatActivity() {
   lateinit var recyclerView: RecyclerView
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)
        recyclerView = findViewById(R.id.userlisrtrecyclerview)
        progressBar = findViewById(R.id.progressbare)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(layoutManager)
        getlist()
    }

    private fun getlist() {
        progressBar!!.visibility = View.VISIBLE
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbz390oSR2iEQWgyZ0JgyD33ikiPEDEMBKj1tpAKMFeW8YToOCeWrmkUW3ZS6mp3SQDL/exec?action=getItems",
            { response -> parsresponse(response) }
        ) { error ->
            progressBar!!.visibility = View.GONE
            Toast.makeText(this@Userlist, "error" + error.message, Toast.LENGTH_SHORT).show()
        }
        val soketTimeout = 50000
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(soketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        val requestQueue = Volley.newRequestQueue(this@Userlist)
        requestQueue.add(stringRequest)
    }

    private fun parsresponse(response: String) {
        val arraylist: ArrayList<EmailModel> = ArrayList<EmailModel>()
        var jobj: JSONObject? = null
        progressBar!!.visibility = View.GONE
        try {
            jobj = JSONObject(response)
            val jsonArray = jobj.getJSONArray("items")
            for (i in 0 until jsonArray.length()) {
                val jo = jsonArray.getJSONObject(i)
                val student = EmailModel()
                student.studentemail=jo.getString("studentemail");
                student.studentNumber=jo.getString("studentNumber");
                student.studentId = jo.getString("studentId");
                student.studentdate=jo.getString("studentdate");
                arraylist.add(student)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val studentAdapter = EmailAdapter(arraylist, applicationContext)
        recyclerView!!.adapter = studentAdapter
        progressBar!!.visibility = View.GONE
    }
}