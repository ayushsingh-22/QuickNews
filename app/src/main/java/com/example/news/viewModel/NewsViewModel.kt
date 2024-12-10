import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.breakingNewsDataClass.News
import data.breakingNewsDataClass.NewsResponse
import data.detailNewsDataClass.DetailNews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import repository.BreakingNewsRespository
import repository.DetailNewsRepository

private val Context.dataStore by preferencesDataStore(name = "NewsCache")

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val context by lazy { application.applicationContext } // use the application context

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val newsKey = stringPreferencesKey("CurrentTopNewsData")

    fun getTopNews(country: String, language: String, date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Step 1: Load cached data from DataStore
                val cachedDataJson = context.dataStore.data.first()[newsKey]
                if (!cachedDataJson.isNullOrEmpty()) {
                    val cachedDataType = object : TypeToken<List<News>>() {}.type
                    val cachedNewsList: List<News> = Gson().fromJson(cachedDataJson, cachedDataType)
                    _newsList.value = cachedNewsList
                }

                // Step 2: Fetch fresh data from API
                val response = BreakingNewsRespository().fetchTopNews(country, language, date)
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse: NewsResponse ->
                        val latestNews = newsResponse.top_news.flatMap { it.news }
                        _newsList.value = latestNews

                        // Step 3: Cache the latest data in DataStore
                        context.dataStore.edit { preferences ->
                            preferences[newsKey] = Gson().toJson(latestNews)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace() // Handle exception properly (e.g., logging or user feedback)
            } finally {
                _isLoading.value = false
            }
        }
    }
}


class DetailNewsViewModel(private val repository: DetailNewsRepository) : ViewModel() {

    private val _newsData = MutableStateFlow<DetailNews?>(null)
    val newsData: StateFlow<DetailNews?> = _newsData

    fun getDetailNews(url: String) {
        viewModelScope.launch {
            try {
                val news = repository.fetchDetailNews(url)
                _newsData.value = news
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class DetailNewsViewModelFactory(private val repository: DetailNewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailNewsViewModel::class.java)) {
            return DetailNewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

