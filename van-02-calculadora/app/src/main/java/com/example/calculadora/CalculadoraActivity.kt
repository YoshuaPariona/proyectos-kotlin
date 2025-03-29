package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.calculadora.databinding.ActivityCalculadoraBinding

class CalculadoraActivity : ComponentActivity() {

    private lateinit var binding: ActivityCalculadoraBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mensaje = intent.getStringExtra("mensaje") ?: "Bienvenido a la calculadora"

        var num1 = Integer.parseInt(binding.etNum1.text.toString())
        var num2 = Integer.parseInt(binding.etNum2.text.toString())

        binding.tvMensaje.text = mensaje

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.calcSum.setOnClickListener{
            var resultado = num1 + num2
        }
    }

}