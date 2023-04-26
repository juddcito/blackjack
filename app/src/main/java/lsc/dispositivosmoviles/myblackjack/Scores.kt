package lsc.dispositivosmoviles.myblackjack

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

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
            LazyColumn {
                items(instanceList) { elemento ->
                    PlayerCard(elemento)
                }
            }
        }
    }
}

@Composable
fun PlayerCard(myPlayer: Player) {
    var playerCards = remember { mutableStateListOf<Int>() }
    myPlayer.cards?.let { it -> playerCards.addAll(it.map { it.toInt() }) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(32.dp)
            .border(width = 1.dp, color = Color(0XFFFDF7C3))
            .padding(12.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            myPlayer.name?.let {
                Text(
                    text = it.uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (myPlayer.win){
                        true -> Color(0xFF829460)
                        else -> Color(0XFFE26868)
                    }
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "VS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "CRUPIER",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = when (myPlayer.win){
                    false -> Color(0xFFFF829460)
                    else -> Color(0XFFE26868)
                })
        }
        myPlayer.date?.let { Text(text = it, fontSize = 20.sp, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
        myPlayer.hour?.let { Text(text = it, fontSize = 20.sp, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }

        LazyRow {
            items(playerCards) { image ->
                var cardStringTemporal = "carta_$image"
                val drawableId = getDrawableIdByName(cardStringTemporal, context)
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    MyBlackjackTheme {

    }
}