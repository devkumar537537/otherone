package com.example.retrofitnice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.datatextview);
        apiServices = ApiClient.getRetrofit().create(ApiServices.class);


//getpostlist();

//getCommentst();
getonepost();


    }

    private void getonepost() {
        Call<Postclass> call = apiServices.getPsotWithField("Android",23,"It is justtesting");
        call.enqueue(new Callback<Postclass>() {
            @Override
            public void onResponse(Call<Postclass> call, Response<Postclass> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Postclass postclass = response.body();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("id => "+postclass .getId()+"\n");
                stringBuilder.append("title => "+postclass .getTitle()+"\n");
                stringBuilder.append("body => "+postclass .getBody()+"\n");
                stringBuilder.append("userId => "+postclass.getUserId()+"\n\n");

                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<Postclass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCommentst() {

        Call<ArrayList<Comments>> call = apiServices.getCommentst(3);

        call.enqueue(new Callback<ArrayList<Comments>>() {
            @Override
            public void onResponse(Call<ArrayList<Comments>> call, Response<ArrayList<Comments>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for(Comments cmmentlist : response.body())
                {
                    stringBuilder.append("id => "+cmmentlist.getId()+"\n");
                    stringBuilder.append("email => "+cmmentlist.getEmail()+"\n");
                    stringBuilder.append("body => "+cmmentlist.getBody()+"\n");
                    stringBuilder.append("name => "+cmmentlist.getName()+"\n\n");

                }
                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Comments>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "eror "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getpostlist() {
        Call<ArrayList<Postclass>> call = apiServices.getPostlist();
        call.enqueue(new Callback<ArrayList<Postclass>>() {
            @Override
            public void onResponse(Call<ArrayList<Postclass>> call, Response<ArrayList<Postclass>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for(Postclass postclass : response.body())
                {
                    stringBuilder.append("id => "+postclass.getId()+"\n");
                    stringBuilder.append("userid => "+postclass.getUserId()+"\n");
                    stringBuilder.append("title => "+postclass.getTitle()+"\n");
                    stringBuilder.append("body => "+postclass.getBody()+"\n\n");

                }
                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Postclass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}