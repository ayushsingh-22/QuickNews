package bottomnav_screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quicknews.data.Authmodel

@Composable
fun home(authViewModel: Authmodel) {

    val authState = authViewModel.authState.observeAsState()

    Column(modifier = Modifier.offset(250.dp)) {
        Text(text = "Home", modifier = Modifier.size(100.dp))
        Button(onClick = {

            authViewModel.signout()
        }) {
            Text(text = "Home")

        }
    }

}