package lsc.dispositivosmoviles.myblackjack

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
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
    var theme = MySingleton.theme

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
        val goal = remember { mutableStateOf("21") }
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Escriba su nombre") },
            modifier = Modifier.background(Color.White)
        )
        Spacer(modifier = Modifier.height(64.dp))
        TextField(
            value = goal.value,
            onValueChange = {
                if (goal.value.isDigitsOnly()) {
                    goal.value = it
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = "Escriba la meta de puntos") },
            modifier = Modifier.background(Color.White)
        )
        Spacer(modifier = Modifier.height(102.dp))
        Button(onClick = {
            if (name.value.isNotBlank() && goal.value.isNotBlank()){
                val intent = Intent(context, InGame::class.java)
                intent.putExtra("name", name.value)
                intent.putExtra("goal", goal.value)
                ContextCompat.startActivity(context, intent, null)
            } else {
                val toast = Toast.makeText(context, "Ingrese su nombre y la meta de puntos!", Toast.LENGTH_LONG)
                toast.show()
            }

        },
        modifier = Modifier.width(290.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = when (theme) {
                    "Light Blue" -> Color(0XFF6C9BCF)
                    "Light Red" -> Color(0XFFC37B89)
                    "Light Green" -> Color(0XFFBCCC9A)
                    else -> Color(0XFFEDEDED)
                }
            )){
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