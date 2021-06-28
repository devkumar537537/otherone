package com.example.retorfiteexxample9am;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    static Retrofit getRetrofit()
    {
        retrofit2.Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

