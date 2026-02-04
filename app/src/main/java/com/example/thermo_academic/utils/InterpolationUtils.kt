package com.example.thermo_academic.utils

object InterpolationUtils {

    fun linear(
        x: Double,
        x1: Double,
        x2: Double,
        y1: Double,
        y2: Double
    ): Double {
        return y1 + (x - x1) * (y2 - y1) / (x2 - x1)
    }
}
