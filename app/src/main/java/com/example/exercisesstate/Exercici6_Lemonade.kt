
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercisesstate.R
import kotlin.random.Random

@Composable
fun Lemonade() {
    val images = arrayOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    val texts = arrayOf(
        "Agafa una llimona",
        "Esprem la llimona",
        "Beu-te-la",
        "Comença de nou"
    )

    var currentIndex by remember { mutableStateOf(0) }
    var squeezeClicksLeft by remember { mutableStateOf(0) } // Contador de clics en la imagen 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen que cambia al hacer clic
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = texts[currentIndex],
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    if (currentIndex == 1) { // Si está en "Esprem la llimona"
                        if (squeezeClicksLeft == 0) {
                            squeezeClicksLeft = Random.nextInt(2, 6) // Asigna de 2 a 5 clics aleatorios
                        }
                        squeezeClicksLeft-- // Reducir clics restantes
                        if (squeezeClicksLeft == 0) {
                            currentIndex++ // Avanza solo si llegó a 0
                        }
                    } else {
                        currentIndex = (currentIndex + 1) % images.size
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto asociado a la imagen
        Text(
            text = texts[currentIndex],
            fontSize = 18.sp
        )
    }
}
