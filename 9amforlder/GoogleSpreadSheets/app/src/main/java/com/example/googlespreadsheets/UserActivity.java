package com.example.googlespreadsheets;

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

public class UserActivity extends AppCompatActivity {
RecyclerView recyclerView;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView = findViewById(R.id.userlisrtrecyclerview);
        progressBar = findViewById(R.id.progressbare);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        getlist();

    }

    private void getlist() {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbyQMQgSb6mo_0OQ8a2EO0Y-Ogn_K67k2GP8UvgrAJjtVGrlwWURLagU/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parsItems(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UserActivity.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        );


        int soketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(soketTimeout,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(UserActivity.this);
        requestQueue.add(stringRequest);

    }

    private void parsItems(String response) {
        ArrayList<Student> arraylist = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(response);
            JSONArray jsonArray = jobj.getJSONArray("items");

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jo = jsonArray.getJSONObject(i);

                Student student = new Student();

                student.setStudentemail(jo.getString("studentemail"));
                student.setStudentNumber(jo.getString("studentNumber"));
                student.setStudentId(jo.getString("studentId"));
                student.setStudentdate(jo.getString("studentdate"));
                arraylist.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StudentAdapter studentAdapter = new StudentAdapter(arraylist,getApplicationContext());

        recyclerView.setAdapter(studentAdapter);
        progressBar.setVisibility(View.GONE);
    }
}