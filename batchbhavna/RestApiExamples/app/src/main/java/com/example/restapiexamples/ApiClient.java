package com.example.restapiexamples;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    static Retrofit getRetroft()
    {
        retrofit2.Retrofit retrofitbuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofitbuilder;
    }

}
