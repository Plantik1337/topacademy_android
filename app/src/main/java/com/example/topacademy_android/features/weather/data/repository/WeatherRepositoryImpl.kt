package com.example.topacademy_android.features.weather.data.repository

import com.example.topacademy_android.features.weather.data.remote.WeatherApiService
import com.example.topacademy_android.features.weather.data.remote.mapper.toDomain
import com.example.topacademy_android.features.weather.domain.model.WeatherForecast
import com.example.topacademy_android.features.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {
    
    override suspend fun getForecast(latitude: Double, longitude: Double): Result<WeatherForecast> {
        return try {
            val response = apiService.getForecast(
                latitude = latitude,
                longitude = longitude,
                apiKey = WeatherApiService.API_KEY
            )
            
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                if (weatherResponse != null) {
                    Result.success(weatherResponse.toDomain())
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 