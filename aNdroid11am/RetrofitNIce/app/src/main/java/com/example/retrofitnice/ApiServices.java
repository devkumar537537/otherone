package com.example.retrofitnice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {


    @GET("posts")
    Call<ArrayList<Postclass>> getPostlist();

    @GET("posts/{id}/comments")
    Call<ArrayList<Comments>> getCommentst(
            @Path("id") int id
    );
    
     @GET("posts/{id}")
     Call<Postclass> getsinglpost(
             @Path("id") int id
     );


    @FormUrlEncoded
    @POST("posts")
    Call<Postclass> getPsotWithField(
            @Field("title") String title,
            @Field("userId") int userId,
            @Field("body") String body
    );
}




