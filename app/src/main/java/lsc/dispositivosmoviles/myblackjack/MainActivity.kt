package lsc.dispositivosmoviles.myblackjack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import lsc.dispositivosmoviles.myblackjack.ui.theme.MyBlackjackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBlackjackTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MyBlackJackApp()
                }
            }
        }
    }
}

@Composable
fun MyBlackJackApp() {
    val options = listOf("Light Blue", "Light Red", "Light Green")
    var theme = MySingleton.theme
    var buttonColor by remember { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, Color.Gray)
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "BLACKJACK 21", fontSize = 42.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "icon",
                modifier = Modifier.size(200.dp),
            )
            Spacer(modifier = Modifier.height(72.dp))
            Button(
                modifier = Modifier.size(200.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when (theme) {
                        "Light Blue" -> Color(0XFF6C9BCF)
                        "Light Red" -> Color(0XFFC37B89)
                        "Light Green" -> Color(0XFFBCCC9A)
                        else -> Color(0XFFEDEDED)
                    }
                ),
                onClick = {
                    val intent = Intent(context, PreGame::class.java)
                    ContextCompat.startActivity(context, intent, null)
                })
            {
                Text(text = "JUGAR!")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.size(200.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when (theme) {
                        "Light Blue" -> Color(0XFF6C9BCF)
                        "Light Red" -> Color(0XFFC37B89)
                        "Light Green" -> Color(0XFFBCCC9A)
                        else -> Color(0XFFEDEDED)
                    }
                ),
                onClick = {
                    val intent = Intent(context, Instructions::class.java)
                    ContextCompat.startActivity(context, intent, null)
                })
            {
                Text(text = "CÓMO JUGAR?")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.size(200.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when (theme) {
                        "Light Blue" -> Color(0XFF6C9BCF)
                        "Light Red" -> Color(0XFFC37B89)
                        "Light Green" -> Color(0XFFBCCC9A)
                        else -> Color(0XFFEDEDED)
                    }
                ),
                onClick = {
                    val intent = Intent(context, Scores::class.java)
                    ContextCompat.startActivity(context, intent, null)
                })
            {
                Text(text = "SCORES")
            }
            Spacer(modifier = Modifier.height(48.dp))
            var selectedOption by remember { mutableStateOf(options[0]) }
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier
                .padding(15.dp)
                .width(200.dp)
                .clickable { expanded = true }
                .border(width = 1.dp, color = Color.Gray, shape = RectangleShape)
                .background(Color.White)
            ){
                Text("Selecciona un tema", modifier = Modifier.padding(16.dp))
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        // acción al hacer clic en el primer elemento
                        buttonColor = "Light Blue"
                        MySingleton.theme = buttonColor
                        expanded = false
                    }) {
                        Text("Light Blue")
                    }
                    DropdownMenuItem(onClick = {
                        // acción al hacer clic en el segundo elemento
                        buttonColor = "Light Red"
                        MySingleton.theme = buttonColor
                        expanded = false
                    }) {
                        Text("Light Red")
                    }
                    DropdownMenuItem(onClick = {
                        // acción al hacer clic en el tercer elemento
                        buttonColor = "Light Green"
                        MySingleton.theme = buttonColor
                        expanded = false
                    }) {
                        Text("Light Green")
                    }
                    DropdownMenuItem(onClick = {
                        // acción al hacer clic en el tercer elemento
                        buttonColor = "Default"
                        MySingleton.theme = buttonColor
                        expanded = false
                    }) {
                        Text("Default")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun DefaultPreview() {
    MyBlackjackTheme {
        MyBlackJackApp()
    }
}