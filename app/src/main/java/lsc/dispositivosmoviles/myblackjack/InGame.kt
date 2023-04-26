package lsc.dispositivosmoviles.myblackjack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import lsc.dispositivosmoviles.myblackjack.ui.theme.MyBlackjackTheme
import java.util.*

class InGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val name = intent.getStringExtra("name")
        val goal = intent.getStringExtra("goal")
        setContent {
            MyBlackjackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Guardamos fecha, hora y score
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH) + 1
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)
                    var completeHour = hour.toString() + ":" + minute
                    var completeDate: String =
                        day.toString() + "/" + month.toString() + "/" + year.toString()
                    var cardsArray = listOf<String>()
                    var player = Player(
                        name = name,
                        goal = goal,
                        date = completeDate,
                        hour = completeHour,
                        cards = cardsArray.toMutableList(),
                        win = false
                    )
                    startPlayerHand()
                    InGameApp(player)
                }
            }
        }
    }
}

fun getDrawableIdByName(name: String, context: Context): Int {
    return context.resources.getIdentifier(name, "drawable", context.packageName)
}

var firstCard = 0
var secondCard = 0
var score = 0

fun checkCardValue(number: Int): Int {
    when (number) {
        in 1..4 -> return 1
        in 5..8 -> return 2
        in 9..12 -> return 3
        in 13..16 -> return 4
        in 17..20 -> return 5
        in 21..24 -> return 6
        in 25..28 -> return 7
        in 29..32 -> return 8
        in 33..36 -> return 9
        in 37..40 -> return 10
        in 41..44 -> return 10
        in 45..48 -> return 10
        in 49..52 -> return 10
        else -> return 0
    }
}

@Composable
fun startPlayerHand() {
    firstCard = (1..52).random()
    secondCard = (1..52).random()
    while (firstCard == secondCard) {
        secondCard = (1..52).random()
    }
    score += checkCardValue(firstCard)
    score += checkCardValue(secondCard)
}

@Composable
fun InGameApp(player: Player) {
    var theme = MySingleton.theme
    val context = LocalContext.current
    var crupierScore: Int by remember { mutableStateOf(0) }
    var playerScore: Int by remember { mutableStateOf(0) }
    var statusBoton by remember { mutableStateOf(false) }
    var startStatus by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var winner by remember { mutableStateOf("empate") }
    var usedCards = arrayOf<Int>()
    var playerCards = remember { mutableStateListOf<Int>() }
    var crupierCards = remember { mutableStateListOf<Int>() }
    var arrayPlayerCards = remember { mutableStateListOf<Int>() }
    var arrayCrupierCards = remember { mutableStateListOf<Int>() }
    var id = MySingleton.id
    var surpriseCrupierCard = 0
    var drawableSurpriseId = 0
    var surpriseCrupierCardString = ""
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
                .padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "CRUPIER",
                modifier = Modifier
                    .width(280.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                text = crupierScore.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            LazyRow {
                items(crupierCards) { image ->
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = player.name.toString().uppercase(),
                modifier = Modifier
                    .width(280.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                text = playerScore.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            LazyRow {
                items(playerCards) { image ->
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        id += 1
                        MySingleton.id = id
                        arrayPlayerCards.clear()
                        arrayCrupierCards.clear()
                        statusBoton = false
                        startStatus = false
                        playerScore = 0
                        crupierScore = 0
                        playerCards.apply {
                            clear()
                        }
                        crupierCards.apply {
                            clear()
                        }
                        statusBoton = true
                        Log.d("Mensajito", id.toString())
                        // Repartir carta a crupier
                        var temporalCard = (1..52).random()

                        // Verificar valores de As
                        if (temporalCard == 1 || temporalCard == 2 || temporalCard == 3 || temporalCard == 4){
                            if ((crupierScore + 11) > 21) {
                                crupierScore += 1
                            } else {
                                crupierScore += 11
                            }
                        } else {
                            crupierScore += checkCardValue(temporalCard)
                        }

                        usedCards.plus(temporalCard)
                        var cardStringTemporal = "carta_$temporalCard"
                        val drawableId = getDrawableIdByName(cardStringTemporal, context)
                        crupierCards.add(drawableId)
                        crupierCards.add(R.drawable.surprise)
                        arrayCrupierCards.add(temporalCard)

                        // Repartir cartas a player
                        for (i in 1..2){
                            temporalCard = (1..52).random()
                            while (usedCards.contains(temporalCard)) {
                                temporalCard = (1..52).random()
                            }
                            //Verificar Ases
                            if (temporalCard == 1 || temporalCard == 2 || temporalCard == 3 || temporalCard == 4){
                                if ((playerScore + 11) > 21) {
                                    playerScore += 1
                                } else {
                                    playerScore += 11
                                }
                            } else {
                                playerScore += checkCardValue(temporalCard)
                            }
                            usedCards.plus(temporalCard)
                            arrayPlayerCards.add(temporalCard)
                            var cardStringTemporal = "carta_$temporalCard"
                            val drawableId = getDrawableIdByName(cardStringTemporal, context)
                            playerCards.add(drawableId)
                        }
                    }, enabled = startStatus,
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = when (theme) {
                            "Light Blue" -> Color(0XFF6C9BCF)
                            "Light Red" -> Color(0XFFC37B89)
                            "Light Green" -> Color(0XFFBCCC9A)
                            else -> Color(0XFFEDEDED)
                        }
                    )
                ) {
                    Text(text = "EMPEZAR!")
                }
                Button(
                    onClick = {
                        val myNewPlayer = Player(player.name, player.goal, player.date, player.hour, player.cards, player.win)
                        var temporalCard = (1..52).random()
                        while (usedCards.contains(temporalCard)) {
                            temporalCard = (1..52).random()
                        }
                        //Verificar Ases
                        if (temporalCard == 1 || temporalCard == 2 || temporalCard == 3 || temporalCard == 4){
                            if ((playerScore + 11) > 21) {
                                playerScore += 1
                            } else {
                                playerScore += 11
                            }
                        } else {
                            playerScore += checkCardValue(temporalCard)
                        }
                        usedCards.plus(temporalCard)
                        arrayPlayerCards.add(temporalCard)
                        var cardStringTemporal = "carta_$temporalCard"
                        val drawableId = getDrawableIdByName(cardStringTemporal, context)
                        playerCards.add(drawableId)
                        for (value in arrayCrupierCards) {
                            Log.d("Mensajito", "Value arrayCrupier: $value")
                        }
                        for (value in arrayPlayerCards) {
                            Log.d("Mensajito", "Value arrayPlayer: $value")
                        }
                        if (playerScore > player.goal?.toInt() ?: 21){
                            winner = "crupier"
                            myNewPlayer.win = false
                            val listaCartas = arrayCrupierCards.map { it.toString() }
                            myNewPlayer.cards = listaCartas.toMutableList()
                            showDialog = true
                            val attributeName = "instance$id"
                            val field = MySingleton::class.java.getDeclaredField(attributeName)
                            field.isAccessible = true
                            field.set(MySingleton, myNewPlayer)
                            player.cards?.clear()
                            startStatus = true
                            statusBoton = false
                        }
                    }, enabled = statusBoton,
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = when (theme) {
                            "Light Blue" -> Color(0XFF6C9BCF)
                            "Light Red" -> Color(0XFFC37B89)
                            "Light Green" -> Color(0XFFBCCC9A)
                            else -> Color(0XFFEDEDED)
                        }
                    )
                ) {
                    Text(text = "PEDIR")
                }
                Button(
                    onClick = {
                        val myNewPlayer = Player(player.name, player.goal, player.date, player.hour, player.cards, player.win)
                        var temporalCard = 0
                        crupierCards.removeAt(1)
                        while (crupierScore <= player.goal?.toInt() ?: 21) {
                            if (crupierScore < 17){
                                temporalCard = (1..52).random()
                                //Verificar Ases
                                if (temporalCard == 1 || temporalCard == 2 || temporalCard == 3 || temporalCard == 4){
                                    if ((crupierScore + 11) > 21) {
                                        crupierScore += 1
                                    } else {
                                        crupierScore += 11
                                    }
                                } else {
                                    crupierScore += checkCardValue(temporalCard)
                                }
                                arrayCrupierCards.add(temporalCard)
                                var cardStringTemporal = "carta_$temporalCard"
                                val drawableId = getDrawableIdByName(cardStringTemporal, context)

                                crupierCards.add(drawableId)
                            } else {
                                break
                            }
                        }

                        if (crupierScore > player.goal?.toInt() ?: 21){
                            winner = "player"
                            myNewPlayer.win = true
                            val listaCartas = arrayPlayerCards.map { it.toString() }
                            myNewPlayer.cards = listaCartas.toMutableList()
                        } else if (playerScore == crupierScore){
                            winner = "empate"
                            myNewPlayer.win = false
                        } else if (playerScore > crupierScore){
                            winner = "player"
                            myNewPlayer.win = true
                            val listaCartas = arrayPlayerCards.map { it.toString() }
                            myNewPlayer.cards = listaCartas.toMutableList()
                        } else if (crupierScore > playerScore){
                            winner = "crupier"
                            myNewPlayer.win = false
                            val listaCartas = arrayCrupierCards.map { it.toString() }
                            myNewPlayer.cards = listaCartas.toMutableList()
                        }
                        showDialog = true
                        val attributeName = "instance$id"
                        val field = MySingleton::class.java.getDeclaredField(attributeName)
                        field.isAccessible = true
                        field.set(MySingleton, myNewPlayer)
                        startStatus = true
                        statusBoton = false
                        arrayPlayerCards.clear()
                        arrayCrupierCards.clear()
                    },
                    enabled = statusBoton,
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = when (theme) {
                            "Light Blue" -> Color(0XFF6C9BCF)
                            "Light Red" -> Color(0XFFC37B89)
                            "Light Green" -> Color(0XFFBCCC9A)
                            else -> Color(0XFFEDEDED)
                        }
                    )
                ) {
                    Text(text = "PLANTAR")
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(getTitle(winner)) },
                        text = { Text(getContent(winner)) },
                        confirmButton = {
                            TextButton(
                                onClick = { showDialog = false }
                            ) {
                                Text("Aceptar")
                            }
                        }
                    )
                }
                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    },
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = when (theme) {
                            "Light Blue" -> Color(0XFF6C9BCF)
                            "Light Red" -> Color(0XFFC37B89)
                            "Light Green" -> Color(0XFFBCCC9A)
                            else -> Color(0XFFEDEDED)
                        }
                    )
                ) {
                    Text(text = "MENÚ PRINCIPAL")
                }
            }
        }
    }
}

fun getTitle(variable: String): String {
    return when (variable) {
        "player" -> "¡Ganador! :D"
        "crupier" -> "Perdedor :("
        else -> "¡EMPATE!"
    }
}

fun getContent(variable: String): String {
    return when (variable) {
        "player" -> "Le has ganado al Crupier. Felicidades."
        "crupier" -> "Has perdido, suerte para la próxima..."
        else -> "Ha ocurrido un empate, el siguiente juego es el bueno..."
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    MyBlackjackTheme {
        var player = Player("Juan", "21", "12/04/2023", "15:08", listOf("", "", "").toMutableList(), false)
        InGameApp(player)
    }
}