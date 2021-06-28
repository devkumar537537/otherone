package com.example.androidspreadsheets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        recyclerView = findViewById(R.id.userlisrtrecyclerview);
        progressBar = findViewById(R.id.progressbare);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getlist();
    }

    private void getlist() {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://script.google.com/macros/s/AKfycbzJ4tQx9urvqCzbH2aUBCbjag04ly3sphyRSMBiMBHYpXFDWGyN6SXZ/exec?action=getItems",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parsItems(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Userlist.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        );

        int soketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(soketTimeout,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(Userlist.this);
        requestQueue.add(stringRequest);
    }

    private void parsItems(String response) {
        ArrayList<EamilModels> arraylist = new ArrayList<>();

        JSONObject jobj = null;
        try {
            jobj = new JSONObject(response);
            JSONArray jsonArray = jobj.getJSONArray("items");

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jo = jsonArray.getJSONObject(i);

                EamilModels student = new EamilModels();

                student.setEmail(jo.getString("email"));
                student.setNumber(jo.getString("number"));
                student.setId(jo.getString("id"));
                student.setDate(jo.getString("date"));
                arraylist.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EmailAdapter studentAdapter = new EmailAdapter(arraylist,getApplicationContext());

        recyclerView.setAdapter(studentAdapter);
        progressBar.setVisibility(View.GONE);
    }
}