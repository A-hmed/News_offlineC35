package com.route.news_offlinec35.api

import com.route.news_offlinec35.model.ArticlesResponse
import com.route.news_offlinec35.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey:String
    ):Call<SourcesResponse>

    @GET("/v2/everything")
    fun getArticles(
        @Query("apiKey") apiKey:String,
        @Query("sources") sourceId: String
    ):Call<ArticlesResponse>
}