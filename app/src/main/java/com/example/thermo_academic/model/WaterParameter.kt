package com.example.thermo_academic.model

import android.R

enum class WaterParameter(val displayName: String) {
    PRESSURE("Presión (kPa)"),     // kPa
    TEMPERATURE("Temperature (°C)"),  // °C
    VF("vf"),
    VG("vg"),
    HF("hf"),
    HG("hg"),
    SF("sf"),
    SG("sg")
}
