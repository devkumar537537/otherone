package com.example.retorfiteexxample9am;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;

ApiServices apiServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = findViewById(R.id.recyclerviewPost);



               apiServices = ApiClient.getRetrofit().create(ApiServices.class);
               getpost();
               getcomments();
    }

    private void getcomments() {
        ArrayList<Comments> comemtnslist = new ArrayList<>();

        Call<ArrayList<Comments>> commentcall = apiServices.getCommentst(1);
        commentcall.enqueue(new Callback<ArrayList<Comments>>() {
            @Override
            public void onResponse(Call<ArrayList<Comments>> call, Response<ArrayList<Comments>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                for(Comments comments: response.body())
                {


                    comemtnslist.add(comments);
                }
               CommentRecyclerAdapter commentRecyclerAdapter = new CommentRecyclerAdapter(comemtnslist,getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(commentRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Comments>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getpost() {
        ArrayList<PostModel> postlist= new ArrayList<>();
//        Call<ArrayList<PostModel>> callist = apiServices.getPostList();
//        callist.enqueue(new Callback<ArrayList<PostModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
//                if(!response.isSuccessful())
//                {
//                    Toast.makeText(MainActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
//                return;
//                }
//                for(PostModel postModel: response.body())
//                {
//
//
//                    postlist.add(postModel);
//                }
//                RecyclerAdapter recycleradapter = new RecyclerAdapter(postlist,getApplicationContext());
//                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(recycleradapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
  // Call<PostModel>  call = apiServices.getpost(1);
   Call<PostModel> call = apiServices.getPsotWithField("Android",11,"This is android testing");
   call.enqueue(new Callback<PostModel>() {
       @Override
       public void onResponse(Call<PostModel> call, Response<PostModel> response) {
           if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                return;
                }
           PostModel postModel = response.body();
           postlist.add(postModel);
                RecyclerAdapter recycleradapter = new RecyclerAdapter(postlist,getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recycleradapter);
       }

       @Override
       public void onFailure(Call<PostModel> call, Throwable t) {

       }
   });


    }
}