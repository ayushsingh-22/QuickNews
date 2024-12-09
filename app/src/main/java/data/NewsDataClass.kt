package data

// Root data class
data class NewsResponse(
    val top_news: List<TopNews>,
    val language: String,
    val country: String
)

// TopNews contains a list of news articles
data class TopNews(
    val news: List<News>
)

// News represents each individual article
data class News(
    val id: Int,
    val title: String,
    val text: String?,
    val summary: String?,
    val url: String,
    val image: String?,
    val video: String?,
    val publish_date: String,
    val author: String?,
    val authors: List<String>
)

data class paperNewsResponse(
    val front_page: FrontPage
)

// Data class for the 'front_page' field
data class FrontPage(
    val name: String,
    val date: String,
    val language: String,
    val country: String,
    val image: String
)
