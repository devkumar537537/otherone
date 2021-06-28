package com.example.restapiexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;

ServiceInterface serviceInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);

serviceInterface = ApiClient.getRetroft().create(ServiceInterface.class);

//getresponseposts();
getuqniqueresposnt();
    }

    private void getuqniqueresposnt() {
        Call<PostModel> call = serviceInterface.getpostresponse("Testing",67,"tThis is body");
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();


                PostModel postModel= response.body();

                    stringBuilder.append("id => "+postModel.getId()+ "\n");
                    stringBuilder.append("userid => "+postModel.getUserId()+ "\n");
                    stringBuilder.append("title => "+postModel.getTitle()+ "\n");
                    stringBuilder.append("body => "+postModel.getBody());


                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "this is error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getresponseposts() {
        Call<ArrayList<PostModel>> call = serviceInterface.getpostitem();

        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();


                for(PostModel postModel: response.body())
                {
                   stringBuilder.append("id => "+postModel.getId()+ "\n");
                   stringBuilder.append("userid => "+postModel.getUserId()+ "\n");
                   stringBuilder.append("title => "+postModel.getTitle()+ "\n");
                   stringBuilder.append("body => "+postModel.getBody()+"\n\n");
                }

                textView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error is "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}