package navigation_screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.news.R
import com.example.news.ui.theme.nunito
import data.News
import data.getScreenSizes
import viewmodel.NewsViewModel

@Composable
fun home_screen(navController: NavController, newsViewModel: NewsViewModel = viewModel()) {
    val context = LocalContext.current
    val width = getScreenSizes().first.dp
    val height = getScreenSizes().second.dp

    var text by remember { mutableStateOf("") }

    // Observe the ViewModel state
    val newsList by newsViewModel.newsList.collectAsState()
    val isLoading by newsViewModel.isLoading.collectAsState()

    // Trigger the API call once the screen is loaded
    LaunchedEffect(Unit) {
        newsViewModel.getTopNews("us", "en", "2024-12-04")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = 12.dp, y = (height * 0.025f)),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "notification",
            modifier = Modifier
                .size(55.dp)
                .clickable {
                    Toast.makeText(context, "Notification Screen", Toast.LENGTH_SHORT).show()
                }
                .offset(x = -(width * 0.15f))
        )

        Text(
            text = "Quick News",
            fontFamily = nunito,
            fontWeight = FontWeight.SemiBold,
            fontSize = 35.sp,
            modifier = Modifier.offset(x = -(width * 0.09f), y = -(height * 0.0001f))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 12.dp, y = 75.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = text,
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("Search") },
                modifier = Modifier.width((width * 0.8f))
            )
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification",
                modifier = Modifier
                    .size(39.dp)
                    .offset(y = (height * 0.025f))
                    .clickable {
                        Toast.makeText(context, "Notification Screen", Toast.LENGTH_SHORT).show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = "Breaking News",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.size(250.dp),
                fontSize = 30.sp,
            )

            Spacer(modifier = Modifier.width(48.dp))

            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "next screen",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        Toast.makeText(context, "Breaking News Tab", Toast.LENGTH_SHORT).show()
                    }
            )
        }

        if (isLoading) {
            Text(
                text = "Loading...",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(newsList) { news ->
                    NewsCard(news, context)
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: News, context: android.content.Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                context.startActivity(browserIntent)
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Title
            Text(
                text = news.title,
                fontFamily = nunito,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Summary
            news.summary?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    maxLines = 3
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Image
            news.image?.let { imageUrl ->
                Image(
                    painter = painterResource(id = R.drawable.logo), // Replace with Coil or Glide for loading image from URL
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }
        }
    }
}
