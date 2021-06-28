package com.example.spreadsheetexampl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class UserDetail extends AppCompatActivity {

    EditText editname,editnumber,editid,editdate;
    Button updatebtn,deletebtn;
    String number_text,email_text,id_text,date_text;
    ProgressBar detailProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        bindviews();
        getdata();
        updatviews();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text = editname.getText().toString();
                number_text = editnumber.getText().toString();
                id_text = editid.getText().toString();
                date_text = editdate.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzRNDxdRZjSKyfcilao2bvsR6UNSzeYJR9GNUG4EmMcrQPKqi638ypaoNYV31LjngA1/exec",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                detailProgressBar.setVisibility(View.GONE);
                                Toast.makeText(UserDetail.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                detailProgressBar.setVisibility(View.GONE);
                                Toast.makeText(UserDetail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String> params = new HashMap<>();

                        params.put("action","update");
                        params.put("name",email_text);
                        params.put("number",number_text);
                        params.put("id",id_text);
                        params.put("date",date_text);
                        return params;
                    }
                };
                int soketTimeout = 50000;
                RetryPolicy retryPolicy = new DefaultRetryPolicy(soketTimeout,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                stringRequest.setRetryPolicy(retryPolicy);

                RequestQueue requestQueue = Volley.newRequestQueue(UserDetail.this);
                requestQueue.add(stringRequest);

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_text = editid.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,

                        "https://script.google.com/macros/s/AKfycbzRNDxdRZjSKyfcilao2bvsR6UNSzeYJR9GNUG4EmMcrQPKqi638ypaoNYV31LjngA1/exec?action=delete&id="+id_text,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                detailProgressBar.setVisibility(View.GONE);
                                Toast.makeText(UserDetail.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        detailProgressBar.setVisibility(View.GONE);
                        Toast.makeText(UserDetail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                int soketTimeout = 50000;
                RetryPolicy retryPolicy = new DefaultRetryPolicy(soketTimeout,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                stringRequest.setRetryPolicy(retryPolicy);

                RequestQueue requestQueue = Volley.newRequestQueue(UserDetail.this);
                requestQueue.add(stringRequest);
            }
        });
    }


    private void updatviews() {
        editname.setText(email_text);
        editnumber.setText(number_text);
        editid.setText(id_text);
        editdate.setText(date_text);
    }
    private void getdata() {
        if(getIntent() != null)
        {
            email_text = getIntent().getStringExtra("name");
            number_text = getIntent().getStringExtra("number");
            id_text = getIntent().getStringExtra("id");
            date_text = getIntent().getStringExtra("date");
        }


    }
    private void bindviews() {
        editname = findViewById(R.id.updateemail);
        editnumber = findViewById(R.id.update_user_number);
        detailProgressBar = findViewById(R.id.detail_progressbar);
        updatebtn = findViewById(R.id.update_btn);
        deletebtn = findViewById(R.id.delete_btn);
        editid= findViewById(R.id.update_user_id);
        editdate = findViewById(R.id.update_user_date);
    }
}