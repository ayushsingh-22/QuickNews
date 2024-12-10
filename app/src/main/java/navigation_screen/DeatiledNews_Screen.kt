package navigation_screen

import DetailNewsViewModel
import DetailNewsViewModelFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.ui.theme.nunito
import data.shareContent
import repository.DetailNewsRepository

@Composable
fun DetailNewsScreen(newsUrl: String) {

    val context = LocalContext.current
    val repository = DetailNewsRepository()
    val viewModelFactory = DetailNewsViewModelFactory(repository)
    val newsDetailsViewModel: DetailNewsViewModel = viewModel(factory = viewModelFactory)
    val newsDetails by newsDetailsViewModel.newsData.collectAsState()

    LaunchedEffect(newsUrl) {
        newsDetailsViewModel.getDetailNews(newsUrl)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(85.dp)
                    .offset(10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.share_button),
                contentDescription = "shareButton",
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = -22.dp, y = 32.dp)
                    .clickable {
                        shareContent(context, newsUrl) // Call the share function when clicked
                    }
            )
        }

        // Main Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (newsDetails == null) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                newsDetails?.let { detailNews ->
                    // Scrollable Content
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()) // Make the content scrollable
                            .padding(16.dp)
                    ) {
                        // Title
                        Text(
                            text = detailNews.title ?: "No Title Available",
                            fontFamily = nunito,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Justify
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Image
                        if (detailNews.image.isNotEmpty()) {
                            AsyncImage(
                                model = detailNews.image,
                                contentDescription = "News Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(bottom = 16.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Content (Text)
                        Text(
                            text = detailNews.text.ifEmpty { "No Content Available" }, // Handle empty text
                            fontFamily = nunito,
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }
    }
}
