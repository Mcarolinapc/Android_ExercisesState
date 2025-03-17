package com.example.exercisesstate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/*Exercici 3: Endevina el número secret
Crea una app que generi aleatòriament un número entre 0 i 100.
L’usuari haurà d’encertar aquest número, introduïnt a un TextField el valor que creu
que és el correcte, i polsant sobre un botó. El programa comprovarà si el número
introduït és el buscat, i es mostrarà un dels següents missatges:

*/

@Composable
fun NumeroSecreto() {
    var ingresarnum: String by remember { mutableStateOf("0") }
    var showText: Boolean by remember { mutableStateOf(false) }
    var intentos: Int by remember { mutableStateOf(0) }
    var matchin by remember { mutableStateOf(obtenerNumeroAleatorio(0, 3)) }
    var mensaje by remember { mutableStateOf("Ingresa un número entre 0 y 20") }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        TextField(
            value = ingresarnum,
            onValueChange = { ingresarnum = it },
            label = { Text("Ingresa un numero") })
        Button(onClick = {
            showText = true
            val numero = ingresarnum.toIntOrNull()
            if (numero != null) {
                var resultado = matchConteoIntentos(numero, matchin, intentos)
                mensaje = resultado.first
                intentos = resultado.second
            } else {
                mensaje = "Por favor, ingresa un número válido"
            }
        }) {
            Text("Buscar número")
        }
        if (showText) {

            Text("$mensaje , en tu $intentos intento")
        }

    }

}

/*El número que busques és més petit
El número que busques és més gran
Has encertat!
Crea una segona versió de l’app en que es compti el número d’intents fets.*/

fun match(num: String): String {
    var numero = num.toInt()
    var matchin = obtenerNumeroAleatorio(0, 5)
    return when {
        matchin < numero -> "El número que busques és més petit"
        matchin > numero -> "El número que busques és més gran"
        else -> "Has encertat"
    }
}


fun obtenerNumeroAleatorio(min: Int, max: Int): Int {
    return Random.nextInt(min, max + 1)
}

fun matchConteoIntentos(numero: Int, matchin: Int, contador: Int): Pair<String, Int> {
    var intentos = contador + 1
    val result = when {
        matchin < numero -> "El número que buscas es más pequeño"
        matchin > numero -> "El número que buscas es más grande"
        else -> "Has acertado"
    }
    return Pair(result, intentos)
}