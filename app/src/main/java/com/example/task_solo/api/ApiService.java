package com.example.task_solo.api;


import com.example.task_solo.response.BaseResponse;
import com.example.task_solo.response.FeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search?show-fields=thumbnail,trailText")
    Call<BaseResponse> postFeed(
            @Query("api-key") String apiKey,
            @Query("format") String format,
            @Query("page") Integer page,
            @Query("page-size") Integer pageSize
    );
}
