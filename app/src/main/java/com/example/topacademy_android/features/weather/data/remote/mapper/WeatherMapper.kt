package com.example.topacademy_android.features.weather.data.remote.mapper

import com.example.topacademy_android.features.weather.data.remote.dto.WeatherResponseDto
import com.example.topacademy_android.features.weather.data.remote.dto.WeatherItemDto
import com.example.topacademy_android.features.weather.domain.model.WeatherForecast
import com.example.topacademy_android.features.weather.domain.model.WeatherItem
import com.example.topacademy_android.features.weather.domain.model.WeatherType
import kotlin.math.roundToInt

fun WeatherResponseDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        cityName = city.name,
        country = city.country,
        items = list.map { it.toDomain() }
    )
}

fun WeatherItemDto.toDomain(): WeatherItem {
    return WeatherItem(
        dateTime = dateTime,
        dateTimeText = dateTimeText,
        temperature = main.temperature.roundToInt(),
        feelsLike = main.feelsLike.roundToInt(),
        description = weather.firstOrNull()?.description ?: "",
        humidity = main.humidity,
        windSpeed = wind.speed,
        precipitationProbability = (precipitationProbability * 100).roundToInt(),
        weatherType = mapWeatherType(weather.firstOrNull()?.main ?: "")
    )
}

private fun mapWeatherType(weatherMain: String): WeatherType {
    return when (weatherMain.lowercase()) {
        "clear" -> WeatherType.SUNNY
        "clouds" -> WeatherType.CLOUDY
        "rain", "drizzle" -> WeatherType.RAINY
        "thunderstorm" -> WeatherType.STORMY
        "snow" -> WeatherType.SNOWY
        "mist", "fog", "haze" -> WeatherType.FOGGY
        else -> WeatherType.CLOUDY
    }
} 