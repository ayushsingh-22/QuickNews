package navigation_screen

import NewsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
fun BrakingNews_Screen(
    navController: NavController,
    newsViewModel: NewsViewModel = viewModel(),
) {

    var date = getCurrentUTCDate()

    val newsList by newsViewModel.newsList.collectAsState()
    val isLoading by newsViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        newsViewModel.getTopNews("in", "en", date, )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp)
            .background(Color.White)
    ) {

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
                    .size(70.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "Breaking News",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                modifier = Modifier
                    .offset(y = (-2).dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Surface(
            modifier = Modifier
                .fillMaxSize(0.98f)
                .border(2.dp, Color.Black)
        ) {

            Box(contentAlignment = Alignment.Center) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.Red, strokeWidth = 4.dp)
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(newsList) { news ->
                            BreakingNewsCard(news, navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BreakingNewsCard(news: News, navController: NavController) {

    val encodedUrl = java.net.URLEncoder.encode(news.url, "UTF-8")
    Card(
        modifier = Modifier
            .fillMaxWidth() // Each card takes full width
            .padding(8.dp)
            .clickable {
                navController.navigate("detail_news?newsUrl=$encodedUrl")
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp) // Height for the card to maintain 4 cards in view
                .fillMaxWidth()
        ) {
            // Image section
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = news.image,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp) // Image height within the card
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            // Title overlay
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
            ) {
                Text(
                    text = news.title,
                    fontFamily = nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

