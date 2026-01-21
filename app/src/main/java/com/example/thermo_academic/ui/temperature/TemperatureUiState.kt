package com.example.thermo_academic.ui.temperature

sealed class TemperatureUiState {
    data class Success(val result: Double) : TemperatureUiState()
    data class Error(val message: String) : TemperatureUiState()
}
