package navigation_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.news.R
import data.getScreenSizes

@Composable
fun card_design(navController: NavController) {

    val width = getScreenSizes().first.dp
    val height = getScreenSizes().second.dp

    Card(modifier = Modifier.size(width * 0.93f, height * 0.28f)) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")
    }

    Card(modifier = Modifier.size(width * 0.93f, height * 0.28f)) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")
    }

    Card(modifier = Modifier.size(width * 0.93f, height * 0.28f)) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")
    }

    Card(modifier = Modifier.size(width * 0.93f, height * 0.28f)) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")
    }
}