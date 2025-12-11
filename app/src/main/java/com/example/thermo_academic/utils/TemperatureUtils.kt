package com.example.thermo_academic.utils

object TemperatureUtils {

    // Represent units with simple codes
    const val C = "C"
    const val F = "F"
    const val K = "K"

    fun convert(value: Double, from: String, to: String): Double {
        if (from == to) return value
        // Convert input to Celsius as pivot
        val celsius = when (from) {
            C -> value
            F -> (value - 32.0) * 5.0 / 9.0
            K -> value - 273.15
            else -> throw IllegalArgumentException("Unidad desconocida: $from")
        }

        // Convert Celsius to target
        return when (to) {
            C -> celsius
            F -> (celsius * 9.0 / 5.0) + 32.0
            K -> celsius + 273.15
            else -> throw IllegalArgumentException("Unidad desconocida: $to")
        }
    }
}
