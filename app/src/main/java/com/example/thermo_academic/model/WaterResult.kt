package com.example.thermo_academic.model

data class WaterResult(
    val pressure: Double,
    val temperature: Double,
    val vf: Double,
    val vg: Double,
    val hf: Double,
    val hfg: Double,
    val hg: Double,
    val sf: Double,
    val sfg: Double,
    val sg: Double
)
