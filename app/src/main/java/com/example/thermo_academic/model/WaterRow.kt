package com.example.thermo_academic.model

data class WaterRow(
    val pressureKpa: Double,
    val temperatureC: Double,
    val vf: Double,
    val vg: Double,
    val hf: Double,
    val hg: Double,
    val sf: Double,
    val sg: Double
) {

    fun getValue(parameter: WaterParameter): Double {
        return when (parameter) {
            WaterParameter.PRESSURE -> pressureKpa
            WaterParameter.TEMPERATURE -> temperatureC
            WaterParameter.VF -> vf
            WaterParameter.VG -> vg
            WaterParameter.HF -> hf
            WaterParameter.HG -> hg
            WaterParameter.SF -> sf
            WaterParameter.SG -> sg
        }
    }
}
