package com.example.topacademy_android.features.weather.domain.repository

import com.example.topacademy_android.features.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getForecast(latitude: Double, longitude: Double): Result<WeatherForecast>
} 