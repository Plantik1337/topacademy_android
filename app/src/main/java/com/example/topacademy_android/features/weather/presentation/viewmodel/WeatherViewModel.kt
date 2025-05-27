package com.example.topacademy_android.features.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.features.weather.data.remote.WeatherApiService
import com.example.topacademy_android.features.weather.data.repository.WeatherRepositoryImpl
import com.example.topacademy_android.features.weather.domain.model.WeatherForecast
import com.example.topacademy_android.features.weather.domain.usecase.GetWeatherForecastUseCase
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    
    private val apiService = WeatherApiService.create()
    private val repository = WeatherRepositoryImpl(apiService)
    private val getWeatherForecastUseCase = GetWeatherForecastUseCase(repository)
    
    private val _weatherForecast = MutableLiveData<WeatherForecast>()
    val weatherForecast: LiveData<WeatherForecast> = _weatherForecast
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    fun loadWeatherForecast(latitude: Double, longitude: Double) {
        _isLoading.value = true
        _error.value = null
        
        viewModelScope.launch {
            getWeatherForecastUseCase.execute(latitude, longitude).fold(
                onSuccess = { forecast ->
                    _weatherForecast.value = forecast
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "Unknown error occurred"
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun retryLoadWeather(latitude: Double, longitude: Double) {
        loadWeatherForecast(latitude, longitude)
    }
} 