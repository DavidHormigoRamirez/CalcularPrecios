package com.alanturing.cpifp.calcularprecios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alanturing.cpifp.calcularprecios.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rgOpcionesServicio.check(R.id.rb_servicio_malo)
        binding.rgOpcionesServicio.setOnCheckedChangeListener { _, opcion ->
            val cantidadInc = calcularCantidadIncrementada(binding.cuenta.text.toString().toDouble(),opcion)
            binding.cuentaIncrementada.text = cantidadInc.toString()
        }
        binding.botonCalcular.setOnClickListener {
            var cantidadInc = calcularCantidadIncrementada(binding.cuenta.text.toString().toDouble(),
                binding.rgOpcionesServicio.checkedRadioButtonId)
            cantidadInc  /= binding.numeroPersonas.text.toString().toInt()
            val formateado = NumberFormat.getCurrencyInstance().format(cantidadInc)
            binding.totalPorPersona.text = formateado

        }
    }

    private fun calcularCantidadIncrementada(cantidad: Double, opcion: Int): Double {
        val incremento = when (opcion) {
            R.id.rb_servicio_malo -> {
                1.0
            }

            R.id.rb_servicio_normal -> {
                1.1
            }

            else -> {
                1.2
            }
        }

        return cantidad * incremento

    }
}