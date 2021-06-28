package com.example.restapiexamples

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
val BASEURLSECon = "https://quizegame537.000webhostapp.com/"
    fun getRetrofit():Retrofit{
      var retrofitbuilder: retrofit2.Retrofit =   Retrofit.Builder()
            .baseUrl(BASEURLSECon )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitbuilder
    }


}