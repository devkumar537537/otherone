package com.example.retrofitexampleapi;

import com.example.retrofitexampleapi.models.CommentsModels;
import com.example.retrofitexampleapi.models.PostModels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("posts")
    Call<ArrayList<PostModels>>  getpostlist();

    @GET("posts/{id}/comments")
    Call<ArrayList<CommentsModels>> getCommentst(
            @Path("id") int id
    );

    @FormUrlEncoded
    @POST("posts")
    Call<PostModels> getPsotWithField(

            @Field("title") String title,
            @Field("userId") int userId,
            @Field("body") String body
    );

}
