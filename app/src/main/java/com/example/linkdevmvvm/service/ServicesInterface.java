package com.example.linkdevmvvm.service;

import com.example.linkdevmvvm.dataModel.NewsFeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antonio on 1/16/19.
 */

public interface ServicesInterface {

    @GET("articles")
    Call<NewsFeedResponse> getNewsFeed(@Query("source") String source, @Query("apiKey") String apiKey);

}
