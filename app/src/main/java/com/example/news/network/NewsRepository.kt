package repository

import api.ApiClient
import data.NewsResponse
import data.constant_value
import data.paperNewsResponse
import retrofit2.Response

val apiService = ApiClient.apiService

class NewsRepository {

    suspend fun fetchTopNews(
        country: String,
        language: String,
        date: String
    ): Response<NewsResponse> {
        return apiService.getTopNews(constant_value.api3, country, language, date)
    }
}

class PaperNewsRepository() {

    suspend fun getFrontPageNews(
        sourceCountry: String,
        date: String,
        sourceName: String,
    ): paperNewsResponse {
        return apiService.getFrontPageNews(constant_value.api3, sourceCountry, date,sourceName)
    }
}
