package repository

import api.ApiClient
import data.NewsResponse
import data.constant_value
import retrofit2.Response

class NewsRepository {
    private val apiService = ApiClient.apiService

    suspend fun fetchTopNews(
        country: String,
        language: String,
        date: String
    ): Response<NewsResponse> {
        return apiService.getTopNews(constant_value.api, country, language, date)
    }


}
