package com.example.thermo_academic.utils

import com.example.thermo_academic.model.UiPropertyRow
import com.example.thermo_academic.model.WaterResult

object WaterUiMapper {

    fun map(result: WaterResult): List<UiPropertyRow> {
        return listOf(
            UiPropertyRow("Presión", "%.4f".format(result.pressure), "kPa"),
            UiPropertyRow("Temperatura", "%.4f".format(result.temperature), "°C"),
            UiPropertyRow("vf", "%.6f".format(result.vf), "m³/kg"),
            UiPropertyRow("vg", "%.4f".format(result.vg), "m³/kg"),
            UiPropertyRow("hf", "%.4f".format(result.hf), "kJ/kg"),
            UiPropertyRow("hfg", "%.4f".format(result.hfg), "kJ/kg"),
            UiPropertyRow("hg", "%.4f".format(result.hg), "kJ/kg"),
            UiPropertyRow("sf", "%.4f".format(result.sf), "kJ/kg·K"),
            UiPropertyRow("sfg", "%.4f".format(result.sfg), "kJ/kg·K"),
            UiPropertyRow("sg", "%.4f".format(result.sg), "kJ/kg·K")
        )
    }
}
