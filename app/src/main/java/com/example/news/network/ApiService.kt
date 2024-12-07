package api

import data.NewsResponse
import data.constant_value
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


}
