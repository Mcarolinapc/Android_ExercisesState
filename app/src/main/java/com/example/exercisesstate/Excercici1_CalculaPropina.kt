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

/*
Exercici 1 Calcula propina
Crea una app que demani el preu total d’un menú i un percentatge
de propina (mitjançant dos TextField diferents).
Quan l’usuari polsi sobre un botó amb el text “Calcular”
es mostrarà el valor de la propina que hem de deixar, i el preu total (menú més propina).*/

@Composable
fun Propinas(){
    var valorMenu:String by remember { mutableStateOf("0") }
    var porcentajePropina:String by remember { mutableStateOf("0") }
    var showText:Boolean by remember { mutableStateOf(false) }
    var calcularPrecio : Double by remember { mutableStateOf(0.0) }

    Column (  Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        TextField(
            value = valorMenu,
            onValueChange = { valorMenu = it },
            label = { Text("Ingrese elvalor del  menu que ha consunmido" ) })
        TextField(
            value = porcentajePropina,
            onValueChange = { porcentajePropina = it },
            label = { Text("Ingrese el porcentaje de propina") })
        Button(onClick = {
            showText = true
            calcularPrecio = valorMenu.toDouble() * (porcentajePropina.toDouble()/100)
        })
        {
            Text("Calcula")
        }
        if (showText) {
            Text("el valor  $calcularPrecio", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

    }

}