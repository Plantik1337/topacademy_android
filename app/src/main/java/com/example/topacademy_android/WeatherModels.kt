package com.example.topacademy_android

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("list")
    val list: List<WeatherItem>,
    @SerializedName("city")
    val city: City
)

data class WeatherItem(
    @SerializedName("dt")
    val dateTime: Long,
    @SerializedName("main")
    val main: MainWeatherData,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("pop")
    val precipitationProbability: Double,
    @SerializedName("dt_txt")
    val dateTimeText: String
)

data class MainWeatherData(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int
)

data class Weather(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)



data class Wind(
    @SerializedName("speed")
    val speed: Double
)

data class City(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("coord")
    val coordinates: Coordinates
)

data class Coordinates(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)


data class ProcessedWeatherItem(
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