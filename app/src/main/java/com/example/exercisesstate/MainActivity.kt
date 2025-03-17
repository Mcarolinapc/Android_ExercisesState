package com.example.exercisesstate

import Lemonade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dicegame.DiceRoller
import com.example.exercisesstate.ui.theme.ExercisesStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercisesStateTheme {
                //botonEjemplo()
                // Propinas()
                 //Imc()
                //NumeroSecreto
                //Conversor()
                //Lemonade()
                //DiceRoller()
            }
        }
    }
}

@Composable
fun botonEjemplo() {
    var name: String by remember { mutableStateOf("") }
    var showText: Boolean by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") })
        Button(onClick = { showText = true }) {
            Text("Say hello")
        }
        Spacer(Modifier.fillMaxHeight(0.1f))
        if (showText) {
            Text("Hello $name", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercisesStateTheme {
        botonEjemplo()
    }
}