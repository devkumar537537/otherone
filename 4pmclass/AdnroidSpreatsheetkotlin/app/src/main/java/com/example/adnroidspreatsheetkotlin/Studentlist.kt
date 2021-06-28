package com.example.adnroidspreatsheetkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.ArrayList

class Studentlist : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentlist)
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
            "https://script.google.com/macros/s/AKfycbxwG9SSitcLeVkFXQ-5P-KW4dVb-u0ejKUatJn_7rYlkoum7560C3zpBl3MaDKN5yT3/exec?action=getItems",
            { response -> parsresponse(response) }
        ) { error ->
            progressBar!!.visibility = View.GONE
            Toast.makeText(applicationContext, "error" + error.message, Toast.LENGTH_SHORT).show()
        }
        val soketTimeout = 50000
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(soketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)


    }

    private fun parsresponse(response: String?) {
        val arraylist: ArrayList<MyModelclass> = ArrayList<MyModelclass>()
        var jobj: JSONObject? = null
        progressBar!!.visibility = View.GONE


        jobj = JSONObject(response)
        val jsonArray = jobj.getJSONArray("items")

        for (i in 0 until jsonArray.length()) {
            val jo = jsonArray.getJSONObject(i)
            val student = MyModelclass()
            student.studentname=jo.getString("studentname");
            student.studentNumber=jo.getString("studentNumber");
            student.studentId = jo.getString("studentId");
            student.studentdate=jo.getString("studentdate");
            arraylist.add(student)
        }

        val studentAdapter = UserAdapte(arraylist, applicationContext)

        recyclerView!!.adapter = studentAdapter
        progressBar!!.visibility = View.GONE

    }
}