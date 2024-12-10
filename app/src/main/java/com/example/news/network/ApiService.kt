package com.example.news.network

import data.breakingNewsDataClass.NewsResponse
import data.detailNewsDataClass.DetailNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("top-news")
    suspend fun getTopNews(
        @Header("X-Api-Key") apiKey: String,
        @Query("source-country") country: String,
        @Query("language") language: String,
        @Query("date") date: String
    ): Response<NewsResponse>

    @GET("extract-news")
    suspend fun getDetailNews(
        @Header("X-Api-Key") apiKey: String,
        @Query("url") url: String
    ): DetailNews
}

