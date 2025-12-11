package com.example.thermo_academic.ui.temperature

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.thermo_academic.R
import com.example.thermo_academic.viewmodel.TemperatureViewModel
import com.example.thermo_academic.utils.TemperatureUtils

class TemperatureActivity : AppCompatActivity() {

    private lateinit var viewModel: TemperatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)

        // edge-to-edge insets padding
        val mainView: View = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Views
        val edtValue = findViewById<EditText>(R.id.edtValue)
        val spinnerFrom = findViewById<Spinner>(R.id.spinnerFrom)
        val spinnerTo = findViewById<Spinner>(R.id.spinnerTo)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        // ViewModel
        viewModel = ViewModelProvider(this)[TemperatureViewModel::class.java]
        viewModel.resultText.observe(this) { text ->
            txtResult.text = "Resultado: $text"
        }

        // Spinner adapter (units)
        val units = arrayOf(TemperatureUtils.C, TemperatureUtils.F, TemperatureUtils.K)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        // Default selection
        spinnerFrom.setSelection(0) // Celsius
        spinnerTo.setSelection(1)   // Fahrenheit

        // Convert button click
        btnConvert.setOnClickListener {
            val raw = edtValue.text.toString().trim()
            if (raw.isEmpty()) {
                Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = raw.toDoubleOrNull()
            if (value == null) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val from = spinnerFrom.selectedItem as String
            val to = spinnerTo.selectedItem as String

            viewModel.convert(value, from, to)
        }
    }
}
