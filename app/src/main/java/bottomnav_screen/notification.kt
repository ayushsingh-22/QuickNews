package bottomnav_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicknews.R
import com.example.quicknews.data.calculateScreenSize
import com.example.quicknews.ui.theme.nunito

@Composable
fun profile(modifier: Modifier = Modifier) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact_number by remember { mutableStateOf("") }

    val screenSize = calculateScreenSize()
    val screenWidth = screenSize.first.dp
    val screenHeight = screenSize.second.dp
    val context = LocalContext.current

    Card(modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {

            Text(text = "Your Profile",
                fontFamily = nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = colorResource(R.color.black),
                modifier = Modifier.offset( y = 20.dp)
            )

            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "Placeholder Image",
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = 135.dp, y = 110.dp)
                    .clip(CircleShape)
                    .border(1.dp, colorResource(R.color.black), CircleShape)
            )


            Column(modifier = Modifier.offset(x = 30.dp, y = 230.dp)){

                Text(
                    text = "First Name",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                OutlinedTextField(
                    value = name,
                    singleLine = true,
                    onValueChange = { name = it },
                    label = { Text("Enter Name") },
                    modifier = Modifier.clip(RectangleShape)

                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Email",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    onValueChange = { email = it },
                    label = { Text("Enter Email") },
                    modifier = Modifier.clip(RectangleShape)
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Contact Number",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                OutlinedTextField(
                    value = contact_number ,
                    singleLine = true,
                    onValueChange = { contact_number = it },
                    label = { Text("Enter Mobile Number") },
                    modifier = Modifier.clip(RectangleShape)
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Gender",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )


                Spacer(modifier = Modifier.size(20.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.size(280.dp, 40.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text("Save",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp)
                }
            }
    }
}