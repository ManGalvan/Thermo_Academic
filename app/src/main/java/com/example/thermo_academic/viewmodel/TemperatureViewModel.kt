package com.example.thermo_academic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thermo_academic.ui.temperature.TemperatureUiState
import com.example.thermo_academic.utils.TemperatureUtils

class TemperatureViewModel : ViewModel() {

    private val _uiState = MutableLiveData<TemperatureUiState>()
    val uiState: LiveData<TemperatureUiState> = _uiState

    fun convert(rawInput: String, from: String, to: String) {

        if (rawInput.isBlank()) {
            _uiState.value = TemperatureUiState.Error("Ingresa un número")
            return
        }

        val value = rawInput.toDoubleOrNull()
        if (value == null) {
            _uiState.value = TemperatureUiState.Error("Valor inválido")
            return
        }

        val result = TemperatureUtils.convert(value, from, to)
        _uiState.value = TemperatureUiState.Success(result)
    }
}
