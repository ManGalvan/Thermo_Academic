package com.example.thermo_academic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thermo_academic.model.WaterParameter
import com.example.thermo_academic.model.WaterResult
import com.example.thermo_academic.repository.WaterTableRepository

class WaterTableViewModel : ViewModel() {

    private val _result = MutableLiveData<WaterResult?>()
    val result: LiveData<WaterResult?> = _result

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun calculate(
        parameter: WaterParameter,
        value: Double
    ) {
        val bracket = WaterTableRepository.findBracket(parameter, value)

        if (bracket == null) {
            _error.value = "Valor fuera del rango de la tabla"
            _result.value = null
            return
        }

        val interpolated = WaterTableRepository.interpolate(
            bracket = bracket,
            parameter = parameter,
            value = value
        )

        _error.value = null
        _result.value = interpolated
    }
}
