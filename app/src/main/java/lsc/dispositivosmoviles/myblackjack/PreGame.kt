package lsc.dispositivosmoviles.myblackjack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import lsc.dispositivosmoviles.myblackjack.ui.theme.MyBlackjackTheme

class PreGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBlackjackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PreGameApp()
                }
            }
        }
    }
}

@Composable
fun PreGameApp() {
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
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "PRE-GAME",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(35.dp),
            fontSize = 32.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(102.dp))
        val name = remember { mutableStateOf("") }
        val goal = remember { mutableStateOf("") }
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Escriba su nombre") },
            modifier = Modifier.background(Color.White)
        )
        Spacer(modifier = Modifier.height(64.dp))
        TextField(
            value = goal.value,
            onValueChange = { goal.value = it},
            label = { Text(text = "Escriba la meta de puntos") },
            modifier = Modifier.background(Color.White)
        )
        Spacer(modifier = Modifier.height(102.dp))
        Button(onClick = {
            val intent = Intent(context, InGame::class.java)
            intent.putExtra("name", name.value)
            intent.putExtra("goal", goal.value)
            ContextCompat.startActivity(context, intent, null)
        },
        modifier = Modifier.width(290.dp)){
            Text(text = "JUGAR!")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    MyBlackjackTheme {
        PreGameApp()
    }
}