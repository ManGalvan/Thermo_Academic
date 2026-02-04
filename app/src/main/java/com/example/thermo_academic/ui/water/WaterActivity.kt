package com.example.thermo_academic.ui.water

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thermo_academic.R
import com.example.thermo_academic.model.WaterParameter
import com.example.thermo_academic.repository.WaterTableRepository
import com.example.thermo_academic.utils.WaterUiMapper

class WaterActivity : AppCompatActivity() {

    private lateinit var tableAdapter: PropertyTableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_water)

        // Cargar tabla desde CSV
        WaterTableRepository.load(this)

        // Insets
        val mainView: View = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Views
        val spinnerParameter = findViewById<Spinner>(R.id.spinnerParameter)
        val edtValue = findViewById<EditText>(R.id.edtValue)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val recycler = findViewById<RecyclerView>(R.id.recyclerResults)

        // RecyclerView
        tableAdapter = PropertyTableAdapter(emptyList())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = tableAdapter

        // Spinner
        val parameters = WaterParameter.values()
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            parameters.map { it.displayName }
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerParameter.adapter = spinnerAdapter

        // Botón
        btnCalculate.setOnClickListener {
            val rawValue = edtValue.text.toString().trim()

            if (rawValue.isEmpty()) {
                Toast.makeText(this, "Ingresa un valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = rawValue.toDoubleOrNull()
            if (value == null) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedParameter = parameters[spinnerParameter.selectedItemPosition]

            val bracket = WaterTableRepository.findBracket(selectedParameter, value)
            if (bracket == null) {
                Toast.makeText(this, "Valor fuera de rango", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = WaterTableRepository.interpolate(
                bracket,
                selectedParameter,
                value
            )

            val rows = WaterUiMapper.map(result)
            tableAdapter.update(rows)
        }
    }
}
