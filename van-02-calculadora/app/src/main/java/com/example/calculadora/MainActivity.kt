package com.example.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun abrirCalc(view:View) {
        val intent = Intent(this, CalculadoraActivity::class.java)
        intent.putExtra("mensaje", "Iniciando al calculadora correctamente")
        startActivity(intent)
    }
    fun shareApp(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Prueba mi app de calculadora !!!")
        startActivity(Intent.createChooser(intent,"Compartir con . . ."))
    }
}