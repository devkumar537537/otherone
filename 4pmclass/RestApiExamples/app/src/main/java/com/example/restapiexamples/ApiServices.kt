package com.example.restapiexamples

import com.example.restapiexamples.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("posts")
    fun getlist(): Call<MutableList<Postmodels>>

    @GET("posts/{id}/comments")
    fun getcommetlist(
        @Path("id") id:Int
    ): Call<MutableList<Comments>>
    @FormUrlEncoded
    @POST("register.php")
    fun registeryourself(
            @Field("email") email:String,
            @Field("password") password:String,
            @Field("name") name:String
    ):Call<RegisterResponse>


    @GET("getquestion.php")
    fun getquetion(
         @Query("topic") topic:String
    ):Call<Questionlist>


}