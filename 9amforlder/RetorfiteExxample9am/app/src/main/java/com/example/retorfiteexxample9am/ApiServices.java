package com.example.retorfiteexxample9am;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("posts")
    Call<ArrayList<PostModel>> getPostList();

    @GET("posts/{id}/comments")
    Call<ArrayList<Comments>> getCommentst(
            @Path("id") int id
    );
    @GET("posts/{id}")
    Call<PostModel> getpost(
            @Path("id") int id
    );

    @POST("posts")
    Call<PostModel> getPsotWithField(
            @Field("title") String title,
            @Field("userId") int userId,
            @Field("body") String body
    );
}
