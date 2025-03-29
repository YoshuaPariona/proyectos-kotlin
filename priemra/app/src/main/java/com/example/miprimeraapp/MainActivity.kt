package com.example.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.miprimeraapp.ui.theme.MiPrimeraAppTheme
import android.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val tvMensaje = findViewById<TextView>(R.id.tv_mensaje)
        val btnCambiartv = findViewById<Button>(R.id.btn_cambiartv)
        val btnSaludo = findViewById<Button>(R.id.btn_saludo)
        val tvContador = findViewById<TextView>(R.id.tv_contador)
        val btnReset = findViewById<TextView>(R.id.btn_reset)
        var contador = 0

        btnCambiartv.setOnClickListener{
            contador++

            tvMensaje.text = "Texto modificado con éxito"
            tvMensaje.setTextColor(Color.RED)
            btnCambiartv.text = "Bien hecho!"
            tvContador.setText("Has presionado los botones $contador veces")
        }

        btnSaludo.setOnClickListener{
            contador++
            tvMensaje.text = "¡Hola, Yoshua!"
            btnCambiartv.text = "Cambiar el mensaje"
            tvContador.setText("Has presionado los botones $contador veces")
        }

        btnReset.setOnClickListener{
            contador = 0;
            tvMensaje.text = "Bienvenido a mi app en Android"
            btnCambiartv.text = "Cambiar el mensaje"
            tvContador.setText("Has presionado los botones $contador veces")
            tvMensaje.setTextColor(Color.MAGENTA)
        }

    }
}
