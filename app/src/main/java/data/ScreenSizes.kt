package data

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun getScreenSizes(): Pair<Float, Float> {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Convert dp values to pixels for a more accurate calculation
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    return Pair(screenWidthPx, screenHeightPx)
}

