package lsc.dispositivosmoviles.myblackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lsc.dispositivosmoviles.myblackjack.ui.theme.MyBlackjackTheme

class Scores : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBlackjackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val instanceList = mutableListOf<Player>()
                    for (i in 1..10) {
                        val instance = when (i) {
                            1 -> MySingleton.instance1
                            2 -> MySingleton.instance2
                            3 -> MySingleton.instance3
                            4 -> MySingleton.instance4
                            5 -> MySingleton.instance5
                            6 -> MySingleton.instance6
                            7 -> MySingleton.instance7
                            8 -> MySingleton.instance8
                            9 -> MySingleton.instance9
                            10 -> MySingleton.instance10
                            else -> null
                        }
                        if (instance != null) {
                            instanceList.add(instance)
                        }
                    }
                    ScoresApp(instanceList)
                }
            }
        }
    }
}

@Composable
fun ScoresApp(instanceList: List<Player>) {
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
            text = "SCORES",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(35.dp),
            fontSize = 32.sp,
            color = Color.White
        )
        LazyColumn{
            items(instanceList){ elemento ->
               PlayerCard(elemento)
            }
        }
    }
}

@Composable
fun PlayerCard(myPlayer: Player) {
    val listaGeneric: List<Any> = myPlayer.cards.orEmpty().map { it as Int }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(32.dp)
            .border(width = 1.dp, color = Color.Gray)
            .padding(12.dp)
    )
    {
        myPlayer.name?.let {
            Text(
                text = it,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Text(
            text = "VS",
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "CRUPIER",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        myPlayer.date?.let { Text(text = it, fontSize = 20.sp) }
        myPlayer.hour?.let { Text(text = it, fontSize = 20.sp) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    MyBlackjackTheme {

    }
}