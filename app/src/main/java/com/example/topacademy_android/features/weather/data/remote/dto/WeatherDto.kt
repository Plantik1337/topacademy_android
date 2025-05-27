package com.example.topacademy_android.features.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("list")
    val list: List<WeatherItemDto>,
    @SerializedName("city")
    val city: CityDto
)

data class WeatherItemDto(
    @SerializedName("dt")
    val dateTime: Long,
    @SerializedName("main")
    val main: MainWeatherDataDto,
    @SerializedName("weather")
    val weather: List<WeatherDto>,
    @SerializedName("wind")
    val wind: WindDto,
    @SerializedName("pop")
    val precipitationProbability: Double,
    @SerializedName("dt_txt")
    val dateTimeText: String
)

data class MainWeatherDataDto(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int
)

data class WeatherDto(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double
)

data class CityDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("coord")
    val coordinates: CoordinatesDto
)

data class CoordinatesDto(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
) 