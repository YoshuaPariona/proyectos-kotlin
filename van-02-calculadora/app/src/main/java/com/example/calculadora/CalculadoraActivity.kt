package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Spinner
import androidx.activity.ComponentActivity
import com.example.calculadora.databinding.ActivityCalculadoraBinding

class CalculadoraActivity : ComponentActivity() {

    private lateinit var binding: ActivityCalculadoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mensaje = intent.getStringExtra("mensaje") ?: "Bienvenido a la calculadora"
        binding.tvMensaje.text = mensaje

        val operaciones = listOf("Seleccione la operación","Suma", "Resta", "Multiplicación", "División")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operaciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOperaciones.adapter = adapter

        binding.spinnerOperaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val num1 = binding.etNum1.text.toString().toDoubleOrNull()
                val num2 = binding.etNum2.text.toString().toDoubleOrNull()

                if (num1 != null && num2 != null) {
                    val resultado = when (position) {
                        0 ->""
                        1 -> num1 + num2
                        2 -> num1 - num2
                        3 -> num1 * num2
                        4 -> if (num2 != 0.0) num1 / num2 else "Error: División por cero"
                        else -> ""
                    }
                    binding.tvResultado.text = "Resultado: $resultado"
                } else {
                    binding.tvResultado.text = "Por favor ingrese ambos números"
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                binding.tvResultado.text = "Seleccione una operación"
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
