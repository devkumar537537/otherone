package com.example.serviceexample;



import com.example.serviceexample.models.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

  @GET("search")
    Call<Root> getresponseyoutube(
            @Query("part") String part,
            @Query("channelId") String channelId,
            @Query("key") String key
  );
}
