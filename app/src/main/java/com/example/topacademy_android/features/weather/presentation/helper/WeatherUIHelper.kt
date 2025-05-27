package com.example.topacademy_android.features.weather.presentation.helper

import com.example.topacademy_android.R
import com.example.topacademy_android.features.weather.domain.model.WeatherType

object WeatherUIHelper {
    
    fun getBackgroundResource(weatherType: WeatherType): Int {
        return when (weatherType) {
            WeatherType.SUNNY -> R.drawable.weather_sunny_gradient
            WeatherType.CLOUDY -> R.drawable.weather_cloudy_gradient
            WeatherType.RAINY -> R.drawable.weather_rainy_gradient
            WeatherType.STORMY -> R.drawable.weather_rainy_gradient
            WeatherType.SNOWY -> R.drawable.weather_cloudy_gradient
            WeatherType.FOGGY -> R.drawable.weather_cloudy_gradient
        }
    }
    
    fun getWeatherIcon(weatherType: WeatherType): Int {
        return when (weatherType) {
            WeatherType.SUNNY -> android.R.drawable.ic_media_play
            WeatherType.CLOUDY -> android.R.drawable.ic_dialog_info
            WeatherType.RAINY -> android.R.drawable.ic_menu_more
            WeatherType.STORMY -> android.R.drawable.ic_dialog_alert
            WeatherType.SNOWY -> android.R.drawable.ic_dialog_info
            WeatherType.FOGGY -> android.R.drawable.ic_menu_view
        }
    }
} 