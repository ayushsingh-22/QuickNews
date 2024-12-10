package data

import android.content.Intent
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object constant_value {

    val api1 = "963ff70e9fd34fc18400d69a9aa30d95"
    val api2 = "85438ba025a441fdbd555b1a038792ae"
    val api3 = "6ad75245ddaa4b579a89fbc8a1d85996"

}

fun getCurrentUTCDate(): String {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd") // Specify the desired date format
        .withZone(ZoneOffset.UTC)
        .format(Instant.now())
}

fun shareContent(context: android.content.Context, url: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Check out this news: $url") // The message to share
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share via")) // Show a chooser dialog
}
