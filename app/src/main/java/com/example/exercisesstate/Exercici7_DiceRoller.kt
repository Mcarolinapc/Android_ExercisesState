package com.example.dicegame

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import com.example.exercisesstate.R
import kotlin.random.Random

@Composable
fun DiceRoller() {
    val context = LocalContext.current

    // Estados para los dados
    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }

    // Función para lanzar ambos dados
    fun rollBothDice() {
        dice1 = Random.nextInt(1, 7)
        dice2 = Random.nextInt(1, 7)
        checkJackpot(context, dice1, dice2)
    }

    // Función para lanzar un solo dado y verificar JACKPOT
    fun rollSingleDice(isFirstDice: Boolean) {
        if (isFirstDice) {
            dice1 = Random.nextInt(1, 7)
        } else {
            dice2 = Random.nextInt(1, 7)
        }
        checkJackpot(context, dice1, dice2)
    }

    // Contenedor principal con fondo
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.tapestry),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen superior (logo)
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para lanzar ambos dados
            Button(
                onClick = { rollBothDice() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "ROLL THE DICE",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Imágenes de los dados con eventos de clic individuales
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = getDiceDrawable(dice1)),
                    contentDescription = "Dado 1",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { rollSingleDice(true) }
                )

                Image(
                    painter = painterResource(id = getDiceDrawable(dice2)),
                    contentDescription = "Dado 2",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { rollSingleDice(false) }
                )
            }
        }
    }
}

// Función auxiliar para obtener la imagen del dado según el número
fun getDiceDrawable(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

// Función para verificar si hay JACKPOT
fun checkJackpot(context: android.content.Context, dice1: Int, dice2: Int) {
    if (dice1 == 6 && dice2 == 6) {
        Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
    }
}
