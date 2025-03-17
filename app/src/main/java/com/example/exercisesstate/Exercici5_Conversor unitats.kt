package com.example.exercisesstate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*El disseny de l’app constarà dels següents elements gràfics:
Un Text per al títol de l’app.
Un TextField per introduir el valor a transformar.
El factor de conversió es seleccionarà d’un DropdownMenu,
on es mostraran les opcions exposades anteriorment.
Un Button que realitzarà els càlculs i mostrarà un
Text invisible amb el resultat. Aquest Text apareixerà just a sota del Button.
*/
@Composable
fun Conversor() {
    var ingresarNumero: String by remember { mutableStateOf("") }
    var selectedText: String by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val medidas = listOf(
        "de polzada a centimetre",
        "de iarda a metre",
        "de mille a kilometre",
        "de centimetre a polzada",
        "de metre a iarda",
        "de quilometre a milla"
    )
    var resultado1: Double by remember { mutableStateOf(0.0) }
    var showText: Boolean by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Conversor de unidades",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(200.dp))
        TextField(
            value = ingresarNumero,
            onValueChange = { ingresarNumero = it },
            label = { Text("Ingrese un numero") }
        )

        Spacer(Modifier.height(20.dp))


        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            medidas.forEach { opcion ->
                DropdownMenuItem(text = { Text(opcion)}, onClick = {
                    expanded = false
                    selectedText = opcion.trim()
                })
            }
        }
        Button(onClick = {
            showText = true
            val numero = ingresarNumero.toDoubleOrNull() ?: 0.0
            resultado1 = when (selectedText) {
                "de polzada a centimetre" -> polzadaACentimetre(numero)
                "de iarda a metre" -> iardaAMetre(numero)
                "de mille a kilometre" -> millaAQuilometre(numero)
                "de centimetre a polzada" -> centimetreAPolzada(numero)
                "de metre a iarda" -> metreAIarda(numero)
                "de quilometre a milla" -> quilometreAMilla(numero)
                else -> 0.0
            }
        }) {
            Text("Calcular")
        }
        if (showText) {
            Text("El resultado es $resultado1")
        }

    }
}

fun polzadaACentimetre(pulgadas: Double): Double {
    return pulgadas * 2.54
}

fun iardaAMetre(iardas: Double): Double {
    return iardas * 0.9144
}

fun millaAQuilometre(millas: Double): Double {
    return millas * 1.60934
}

fun centimetreAPolzada(centimetros: Double): Double {
    return centimetros / 2.54
}

fun metreAIarda(metros: Double): Double {
    return metros / 0.9144
}

fun quilometreAMilla(kilometros: Double): Double {
    return kilometros / 1.60934
}