package data.detailNewsDataClass

data class DetailNews(
    val title: String,
    val text: String,
    val url: String,
    val image: String,
    val images: List<Image>,
    val video: String,
    val videos: List<Video>,
    val publish_date: String,
    val author: String,
    val authors: List<String>,
    val language: String,
)

data class Image(
    val title: String?,
    val url: String,
    val width: Int,
    val height: Int,
)

data class Video(
    val title: String,
    val url: String,
    val summary: String,
    val duration: Int,
    val thumbnail: String,
)
