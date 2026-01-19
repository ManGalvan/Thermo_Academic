package com.example.thermo_academic.ui.temperature

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.thermo_academic.R
import com.example.thermo_academic.utils.TemperatureUtils
import com.example.thermo_academic.ui.temperature.TemperatureUiState
import com.example.thermo_academic.viewmodel.TemperatureViewModel

class TemperatureActivity : AppCompatActivity() {

    private lateinit var viewModel: TemperatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)

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

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is TemperatureUiState.Success -> {
                    txtResult.text = "Resultado: ${state.result}"
                    edtValue.error = null
                }

                is TemperatureUiState.Error -> {
                    edtValue.error = state.message
                    edtValue.requestFocus()
                }
            }
        }

        // Spinner adapter
        val units = arrayOf(
            TemperatureUtils.C,
            TemperatureUtils.F,
            TemperatureUtils.K
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            units
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        spinnerFrom.setSelection(0) // Celsius
        spinnerTo.setSelection(1)   // Fahrenheit

        btnConvert.setOnClickListener {
            val raw = edtValue.text.toString()
            val from = spinnerFrom.selectedItem as String
            val to = spinnerTo.selectedItem as String

            viewModel.convert(raw, from, to)
        }
    }
}
