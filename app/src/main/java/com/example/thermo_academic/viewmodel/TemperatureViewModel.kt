package com.example.thermo_academic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thermo_academic.utils.TemperatureUtils
import java.text.DecimalFormat

class TemperatureViewModel : ViewModel() {

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText

    private val df = DecimalFormat("#.####") // 4 decimales

    fun convert(value: Double, from: String, to: String) {
        try {
            val res = TemperatureUtils.convert(value, from, to)
            _resultText.value = "${df.format(res)} $to"
        } catch (e: Exception) {
            _resultText.value = "Error: ${e.message}"
        }
    }

    fun clearResult() {
        _resultText.value = "Resultado: —"
    }
}
