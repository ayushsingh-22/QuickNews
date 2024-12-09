package com.example.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.News
import data.NewsResponse
import data.paperNewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.NewsRepository
import repository.PaperNewsRepository

class NewsViewModel : ViewModel() {

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var isDataLoaded = false // Flag to track if data is already fetched


    fun getTopNews(country: String, language: String, date: String) {
        if (isDataLoaded) return // Prevent API call if data is already loaded

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = NewsRepository().fetchTopNews(country, language, date)
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse: NewsResponse ->
                        _newsList.value = newsResponse.top_news.flatMap { it.news }
                    }
                    isDataLoaded = true // Mark data as loaded
                }
            } catch (e: Exception) {
                e.printStackTrace() // Handle the exception as needed
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshData(country: String, language: String, date: String) {
        isDataLoaded = false // Reset the flag
        getTopNews(country, language, date) // Fetch fresh data
    }
}


class PaperNewsViewModel : ViewModel() {

    private val _newsData = MutableStateFlow<paperNewsResponse?>(null)
    val newsData: StateFlow<paperNewsResponse?> = _newsData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchFrontPageNews(sourceCountry: String, date: String, sourceName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = PaperNewsRepository().getFrontPageNews (sourceCountry, date, sourceName)
                _newsData.value = response
                println("Ayush paper " + sourceName)
            } catch (e: Exception) {
                e.printStackTrace() // Handle error properly
            } finally {
                _isLoading.value = false
            }
        }
    }
}
