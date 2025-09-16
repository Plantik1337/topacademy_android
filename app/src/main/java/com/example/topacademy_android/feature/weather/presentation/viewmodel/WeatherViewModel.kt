package com.example.topacademy_android.feature.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.feature.weather.data.remote.dto.SevenTimerDay
import com.example.topacademy_android.feature.weather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val items: List<SevenTimerDay> = emptyList(),
    val error: String? = null
)

class WeatherViewModel(
    private val repo: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state: StateFlow<WeatherUiState> = _state

    fun load(lat: Double, lon: Double) {
        _state.value = WeatherUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val list = repo.getForecast(lat = lat, lon = lon)
                _state.value = WeatherUiState(items = list)
            } catch (e: Exception) {
                _state.value = WeatherUiState(error = e.localizedMessage)
            }
        }
    }
}