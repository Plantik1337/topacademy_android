package com.example.topacademy_android.features.weather.domain.model

data class WeatherForecast(
    val cityName: String,
    val country: String,
    val items: List<WeatherItem>
)

data class WeatherItem(
    val dateTime: Long,
    val dateTimeText: String,
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val humidity: Int,
    val windSpeed: Double,
    val precipitationProbability: Int,
    val weatherType: WeatherType
)

enum class WeatherType {
    SUNNY, CLOUDY, RAINY, STORMY, SNOWY, FOGGY
} 