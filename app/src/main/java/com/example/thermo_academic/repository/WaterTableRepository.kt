package com.example.thermo_academic.repository

import android.content.Context
import com.example.thermo_academic.model.*
import com.example.thermo_academic.utils.CsvUtils
import com.example.thermo_academic.utils.InterpolationUtils

object WaterTableRepository {

    private var table: List<WaterRow> = emptyList()

    fun load(context: Context) {
        table = CsvUtils.readWaterTable(context)
    }

    fun findBracket(parameter: WaterParameter, value: Double): WaterBracket? {
        val sorted = table.sortedBy { it.getValue(parameter) }

        for (i in 0 until sorted.size - 1) {
            val lower = sorted[i]
            val upper = sorted[i + 1]

            val v1 = lower.getValue(parameter)
            val v2 = upper.getValue(parameter)

            if (value in v1..v2) {
                return WaterBracket(lower, upper)
            }
        }
        return null
    }

    fun interpolate(
        bracket: WaterBracket,
        parameter: WaterParameter,
        value: Double
    ): WaterResult {

        val l = bracket.lower
        val u = bracket.upper

        val x1 = l.getValue(parameter)
        val x2 = u.getValue(parameter)

        fun interp(y1: Double, y2: Double) =
            InterpolationUtils.linear(value, x1, x2, y1, y2)

        return WaterResult(
            pressure = interp(l.pressureKpa, u.pressureKpa),
            temperature = interp(l.temperatureC, u.temperatureC),
            vf = interp(l.vf, u.vf),
            vg = interp(l.vg, u.vg),
            hf = interp(l.hf, u.hf),
            hfg = interp(l.hg - l.hf, u.hg - u.hf),
            hg = interp(l.hg, u.hg),
            sf = interp(l.sf, u.sf),
            sfg = interp(l.sg - l.sf, u.sg - u.sf),
            sg = interp(l.sg, u.sg)
        )
    }
}
