package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.IMCTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppIMC(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun MyAppIMC(modifier: Modifier = Modifier){
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableDoubleStateOf(0.0) }

    var pesoDouble = peso.toDoubleOrNull() ?:0.0
    var alturaDouble = altura.toDoubleOrNull() ?:0.0

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calculador IMC",
                textAlign = TextAlign.Center,
                fontSize = 46.sp,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "ídice de masa corporal",
                fontSize = 16.sp
            )
            Spacer(modifier= Modifier.height(80.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(size = 30.dp),
                tonalElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(36.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text="Peso: ",
                            fontSize = 36.sp
                        )

                        TextField(
                            value = peso,
                            onValueChange = {peso = it},
                            label = {Text("70", fontSize = 32.sp)}
                        )


                    }
                    Row(modifier= Modifier.fillMaxWidth()) {
                        Text(
                            text = "Altura: ",
                            fontSize = 32.sp
                        )
                        TextField(
                            value = altura,
                            onValueChange = {altura=it},
                            label = {Text("170", fontSize = 32.sp)}
                        )
                    }
                }
            }
           Spacer(modifier=Modifier.height(30.dp))

            Button(onClick = {
                resultado = pesoDouble * 1000 / (alturaDouble * alturaDouble)
                resultado = String.format("%.3f", resultado).toDouble()
            }
            ) {
                Text("Calcular", fontSize = 32.sp, modifier = Modifier.padding(20.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Resultado:  $resultado",
                fontSize = 40.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MyAppIMCPreview() {
    IMCTheme {
        MyAppIMC()
    }
}