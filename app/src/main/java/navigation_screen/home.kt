package navigation_screen

import NewsViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.ui.theme.nunito
import data.breakingNewsDataClass.News
import data.getCurrentUTCDate

@Composable
fun home_screen(
    navController: NavController,
    newsViewModel: NewsViewModel = viewModel(),
) {
    val context = LocalContext.current

    var text by remember { mutableStateOf("") }
    var date = getCurrentUTCDate()

    // Observe the ViewModel states
    val newsList by newsViewModel.newsList.collectAsState()
    val isLoading by newsViewModel.isLoading.collectAsState()

     //Trigger API calls when the screen is loaded
    LaunchedEffect(Unit) {
       newsViewModel.getTopNews("in", "en", date, )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp)
            .background(Color.White)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .offset(y = (24).dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .offset(y = (-15).dp)
                    .size(65.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Notification Screen", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = "Quick News",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                modifier = Modifier
                    .offset(y = (-8).dp)
            )
        }

        // Search Row
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            OutlinedTextField(
                value = text,
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("Search") },
                modifier = Modifier.weight(22f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification",
                modifier = Modifier
                    .offset(y = 15.dp)
                    .size(45.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Notification Screen", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Breaking News Section
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Breaking News",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.weight(12f))

            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "next screen",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Breaking News Tab", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate("breaking_news")
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Top News Card
        Box(contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.Red, strokeWidth = 4.dp)
            } else {
                LazyRow(
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(newsList.take(4)) { news ->
                        NewsCard(news, navController)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Newspaper Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Front Page",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "next screen",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Newspaper Page Tab", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun NewsCard(news: News, navController: NavController) {
    val encodedUrl = java.net.URLEncoder.encode(news.url, "UTF-8")

    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate("detail_news?newsUrl=$encodedUrl")
            },
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = news.image,
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
            ) {
                Text(
                    text = news.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    maxLines = 2
                )
            }
        }
    }
}

