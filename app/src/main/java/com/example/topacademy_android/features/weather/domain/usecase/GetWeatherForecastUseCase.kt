package com.example.topacademy_android.features.weather.domain.usecase

import com.example.topacademy_android.features.weather.domain.model.WeatherForecast
import com.example.topacademy_android.features.weather.domain.repository.WeatherRepository

class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend fun execute(latitude: Double, longitude: Double): Result<WeatherForecast> {
        return repository.getForecast(latitude, longitude)
    }
} 