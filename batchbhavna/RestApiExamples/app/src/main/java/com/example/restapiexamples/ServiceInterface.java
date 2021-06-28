package com.example.restapiexamples;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceInterface {

    @GET("posts")
Call<ArrayList<PostModel>> getpostitem();

    @GET("posts/{id}/comments")
    Call<ArrayList<Comments>> getcommentlist(
            @Path("id") int id
    );

    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> getpostresponse(
            @Field("title") String title,
            @Field("userId") int userId,
            @Field("body") String body
    );

}
