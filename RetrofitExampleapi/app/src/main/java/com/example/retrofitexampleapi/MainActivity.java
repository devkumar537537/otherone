package com.example.retrofitexampleapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitexampleapi.models.CommentsModels;
import com.example.retrofitexampleapi.models.PostModels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;
    ApiServices apiServices;
    String[] permsissions = {Manifest.permission.INTERNET};
    int permissioncunt =12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewdata);
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permsissions,permissioncunt);
            }
        }
        apiServices = ApiClient.getRerorfit().create(ApiServices.class);
        // getpostlist();

getpostwithfield();

    }

    private void getpostwithfield() {
        Call<ArrayList<CommentsModels>> call = apiServices.getCommentst(2);

        call.enqueue(new Callback<ArrayList<CommentsModels>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentsModels>> call, Response<ArrayList<CommentsModels>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();

                for(CommentsModels postclass : response.body())
                {
                    stringBuilder.append("id => "+postclass.getId()+"\n");
                    stringBuilder.append("userid => "+postclass.getPostId()+"\n");
                    stringBuilder.append("email=> "+postclass.getEmail()+"\n");
                    stringBuilder.append("body => "+postclass.getBody()+"\n");
                    stringBuilder.append("name => "+postclass.getName()+"\n\n");
                }

                textView.setText(stringBuilder.toString());

            }

            @Override
            public void onFailure(Call<ArrayList<CommentsModels>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getpostlist() {
        Call<ArrayList<PostModels>> call = apiServices.getpostlist();

        call.enqueue(new Callback<ArrayList<PostModels>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModels>> call, Response<ArrayList<PostModels>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();

                for(PostModels postclass : response.body())
                {
                    stringBuilder.append("id => "+postclass.getId()+"\n");
                    stringBuilder.append("userid => "+postclass.getUserId()+"\n");
                    stringBuilder.append("title => "+postclass.getTitle()+"\n");
                    stringBuilder.append("body => "+postclass.getBody()+"\n\n");

                }

                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<PostModels>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "errror "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}