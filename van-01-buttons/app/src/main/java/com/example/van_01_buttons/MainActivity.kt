package com.example.van_01_buttons

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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