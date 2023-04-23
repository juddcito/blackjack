package lsc.dispositivosmoviles.myblackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lsc.dispositivosmoviles.myblackjack.ui.theme.MyBlackjackTheme

class Instructions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBlackjackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InstructionsApp()
                }
            }
        }
    }
}

val instructionList = listOf(
    Instruction("1","El objetivo del juego es vencer al crupier obteniendo una puntuación más alta que él sin pasarse de 21."),
    Instruction("2","Cada carta tiene un valor en puntos. Las cartas numeradas de 2 a 10 valen su valor nominal, las figuras (J, Q, K) valen 10 puntos y el as puede valer 1 u 11 puntos."),
    Instruction("3","El crupier reparte dos cartas a cada jugador y una carta boca arriba y otra boca abajo para él."),
    Instruction("4","Los jugadores deben decidir si quieren pedir otra carta (hit) o plantarse (stand)."),
    Instruction("5","Los jugadores pueden pedir cartas hasta que se planten o se pasen de 21."),
    Instruction("6","Una vez que todos los jugadores han terminado de jugar sus manos, el crupier juega su mano."),
    Instruction("7","El jugador gana si su puntuación es mayor que la del crupier sin pasarse de 21."),
    Instruction("8","Si la puntuación del jugador y la del crupier son iguales, se produce un empate y el jugador recupera su apuesta."),
    Instruction("9","Si el jugador se pasa de 21, pierde su apuesta, independientemente de la puntuación del crupier.")
)

@Composable
fun InstructionsApp() {
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
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "INSTRUCCIONES",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(35.dp),
            fontSize = 32.sp,
            color = Color.White
        )
        LazyColumn{
            items(instructionList){ elemento ->
                ElementCard(myInstruction = elemento)
            }
        }
    }
}

@Composable
fun ElementCard(myInstruction: Instruction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(32.dp)
            .border(width = 1.dp, color = Color.Gray)
            .padding(12.dp)
    )
    {
        myInstruction.number?.let {
            Text(text = "Regla #$it",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
              )
        }
        myInstruction.instruction?.let { Text(text = it, fontSize = 20.sp) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyBlackjackTheme {
        InstructionsApp()
    }
}