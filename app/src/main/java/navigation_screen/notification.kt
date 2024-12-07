package navigation_screen

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.news.R


@Composable
fun notification_screen(modifier: NavHostController ) {

    val navcontrol = rememberNavController()

    Card() {

        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
    }
}

