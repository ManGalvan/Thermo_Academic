package com.example.thermo_academic.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.thermo_academic.R
import com.example.thermo_academic.ui.temperature.TemperatureActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTemp = findViewById<Button>(R.id.btnTemperatura)
        btnTemp.setOnClickListener {
            val intent = Intent(this, TemperatureActivity::class.java)
            startActivity(intent)
        }

        val btnPresion = findViewById<Button>(R.id.btnPresion)
        btnPresion.setOnClickListener {
            Toast.makeText(this, "Presión seleccionado", Toast.LENGTH_SHORT).show()
        }

        val btnEnergia = findViewById<Button>(R.id.btnEnergia)
        btnEnergia.setOnClickListener {
            Toast.makeText(this, "Energía seleccionado", Toast.LENGTH_SHORT).show()
        }
    }
}