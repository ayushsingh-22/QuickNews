package bottomnav_screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun search(modifier: Modifier = Modifier) {

    Text(text = "Search", modifier = modifier.size(100.dp))
}