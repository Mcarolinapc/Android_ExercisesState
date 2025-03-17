package com.example.exercisesstate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


/*Crea una app que simuli un entrenador personal. El programa demarà a l’usuari les següents dades:
Nom de l’usuari
Any de naixement.
Alçada.
Pes.
Hi haurà un botó que, al ser polsat, realitzarà uns càlculs i mostrarà el següent:
Nom de l’usuari i edat calculada restant l’any actual de l’any de naixement.
L’IMC (pes/alçada2). A més, en funció del valor de l’IMC, es mostrarà si l’usuari té pes
insuficient (IMC<18,5), normal (18,5>IMC<24,9), sobrepès (25<IMC<50) o obesitat (50<IMC)
*/
@Composable
fun Imc() {
    var nombre: String by remember { mutableStateOf("") }
    var anyoNacmto: String by remember { mutableStateOf("") }
    var altura: String by remember { mutableStateOf("") }
    var peso: String by remember { mutableStateOf("") }
    var showText: Boolean by remember { mutableStateOf(false) }
    var resultado: String by remember { mutableStateOf("") }
    var resultadoEdad: Int by remember { mutableStateOf(0) }
    var mensajeSobrepeso by remember { mutableStateOf("") }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Enter your name") }
        )
        TextField(
            value = anyoNacmto,
            onValueChange = { anyoNacmto = it },
            label = { Text("write your year of birth") }
        )
        TextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("write your  height") }
        )
        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("write your  weight") }
        )


        Button(onClick = {
            showText = true
            resultado = calcularImc(peso, altura).first
            mensajeSobrepeso = calcularImc(peso, altura).second // Se asigna el mensaje de sobrepeso
            resultadoEdad = calcularEdad(anyoNacmto)
        }) {
            Text("Calcular")
        }
        if (showText) {
            Text(
                "Hola $nombre Tu edad es: $resultadoEdad y tu peso está en: $resultado",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

        }

        Spacer(Modifier.fillMaxHeight(0.1f))

        if (mensajeSobrepeso.isNotEmpty()) {
            Text(
                mensajeSobrepeso,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

    }
}
/*L’IMC (pes/alçada2). A més, en funció del valor de l’IMC, es mostrarà si l’usuari té pes
   insuficient (IMC<18,5), normal (18,5>IMC<24,9), sobrepès (25<IMC<50) o obesitat (50<IMC)*/

fun calcularImc(peso:String, altura:String): Pair<String,String> {
    val peso = peso.toDoubleOrNull() ?: return Pair("Error en peso", "")
    val altura = altura.toDoubleOrNull() ?: return Pair("Error en altura", "")

    var imc = peso / (altura * altura)

    return when {
        imc < 18.5 ->Pair("Insuficiente","")
        imc in 18.5..24.9 ->Pair("Normal","")
        imc >= 25.0 && imc < 509.0 -> Pair("Sobrepeso", "Menos Ñam ñam y mas Run run ")
        else -> Pair("Obesidad","Menos Ñam ñam y más Run run")
    }
}

fun calcularEdad(anyoNacimiento:String): Int {
    val anyoNacimiento = anyoNacimiento.toIntOrNull() ?: return -1
    val añoActual = 2025
    return añoActual - anyoNacimiento
}


