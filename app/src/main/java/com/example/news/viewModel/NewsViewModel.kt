package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.News
import data.NewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.NewsRepository

class NewsViewModel : ViewModel() {

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getTopNews(country: String, language: String, date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = NewsRepository().fetchTopNews(country, language, date)
                println("Abhina" + response)
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse: NewsResponse ->
                        _newsList.value = newsResponse.top_news.flatMap { it.news }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
