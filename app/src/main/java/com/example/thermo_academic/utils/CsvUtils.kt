package com.example.thermo_academic.utils

import android.content.Context
import com.example.thermo_academic.model.WaterRow
import java.io.BufferedReader
import java.io.InputStreamReader

object CsvUtils {

    fun readWaterTable(context: Context): List<WaterRow> {
        val list = mutableListOf<WaterRow>()

        val inputStream = context.assets.open("Water.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))

        reader.readLine() // saltar encabezado

        reader.forEachLine { line ->
            val tokens = line.split(",")

            if (tokens.size >= 8) {
                list.add(
                    WaterRow(
                        pressureKpa = tokens[0].toDouble(),
                        temperatureC = tokens[1].toDouble(),
                        vf = tokens[2].toDouble(),
                        vg = tokens[3].toDouble(),
                        hf = tokens[4].toDouble(),
                        hg = tokens[5].toDouble(),
                        sf = tokens[6].toDouble(),
                        sg = tokens[7].toDouble()
                    )
                )
            }
        }

        reader.close()
        return list
    }
}
