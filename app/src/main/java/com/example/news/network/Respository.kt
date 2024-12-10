package repository

import api.ApiClient
import data.breakingNewsDataClass.NewsResponse
import data.constant_value
import data.detailNewsDataClass.DetailNews
import retrofit2.Response

val apiService = ApiClient.apiService

class BreakingNewsRespository {

    suspend fun fetchTopNews(
        country: String,
        language: String,
        date: String
    ): Response<NewsResponse> {
        return apiService.getTopNews(constant_value.api1, country, language, date)
    }
}

class DetailNewsRepository {
    private val apiService = ApiClient.apiService

    suspend fun fetchDetailNews(url: String): DetailNews {
        return apiService.getDetailNews(constant_value.api1,url)
    }
}