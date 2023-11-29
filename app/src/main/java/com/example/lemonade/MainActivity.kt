package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
//      Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .background(colorResource(id = R.color.lemon_dark)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Lemonade",
                Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,

                )
        }

//      Lemonade steps
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(0.9f)
                .background(colorResource(id = R.color.lime_dark)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (step) {
//              Tap the lemon tree
                1 -> LemonButton(
                    image = painterResource(id = R.drawable.lemon_tree),
                    contentDescription = stringResource(id = R.string.tap_lemon_tree),
                    buttonText = stringResource(id = R.string.tap_lemon_tree),
                    buttonFunction = { step++ }
                )
//              Tap to squeeze lemon
                2 -> LemonButton(
                    image = painterResource(id = R.drawable.lemon_squeeze),
                    contentDescription = stringResource(id = R.string.tap_lemon_queeze),
                    buttonText = stringResource(id = R.string.tap_lemon_queeze),
                    buttonFunction = {
                        if ((1..5).random() == 1) {
                            step++
                        }
                    }
                )
//              Drink the lemonade
                3 -> LemonButton(
                    image = painterResource(id = R.drawable.lemonade_glass),
                    contentDescription = stringResource(id = R.string.tap_lemonade_glass),
                    buttonText = stringResource(id = R.string.tap_lemonade_glass),
                    buttonFunction = { step++ }
                )
//              Tap the empty glass to repeat
                4 -> LemonButton(
                    image = painterResource(id = R.drawable.empty_glass),
                    contentDescription = stringResource(id = R.string.tap_empty_glass),
                    buttonText = stringResource(id = R.string.tap_empty_glass),
                    buttonFunction = { step = 1 }
                )
                else -> step = 1
            }
        }
    }


}

@Composable
private fun LemonButton(
    image: Painter,
    contentDescription: String,
    buttonText: String,
    buttonFunction: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(25),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.lime_light)
        ),
        onClick = buttonFunction
    )
    {
        Image(
            painter = image,
            contentDescription = contentDescription
        )
    }
    Text(
        text = buttonText,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}