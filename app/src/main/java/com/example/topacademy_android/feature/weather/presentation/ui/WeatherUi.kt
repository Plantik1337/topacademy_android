package com.example.topacademy_android.feature.weather.presentation.ui

import android.content.Context
import androidx.annotation.ColorRes
import com.example.topacademy_android.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object WeatherUi {

    fun iconFor(code: String): Int = when (code.lowercase()) {
        "clear" -> R.drawable.ic_sunny_24
        "pcloudy", "mcloudy", "cloudy" -> R.drawable.ic_cloud_24
        "rain", "lightrain", "oshower", "ishower", "rainday" -> R.drawable.ic_rain_24
        "snow", "lightsnow" -> R.drawable.ic_snow_24
        "ts", "tsrain" -> R.drawable.ic_thunder_24
        else -> R.drawable.ic_cloud_24
    }

    fun textFor(ctx: Context, code: String): String = when (code.lowercase()) {
        "clear" -> ctx.getString(R.string.weather_clear)
        "pcloudy" -> ctx.getString(R.string.weather_pcloudy)
        "mcloudy" -> ctx.getString(R.string.weather_mcloudy)
        "cloudy" -> ctx.getString(R.string.weather_cloudy)
        "rain", "rainday" -> ctx.getString(R.string.weather_rain)
        "lightrain", "oshower", "ishower" -> ctx.getString(R.string.weather_lightrain)
        "snow" -> ctx.getString(R.string.weather_snow)
        "lightsnow" -> ctx.getString(R.string.weather_lightsnow)
        "ts", "tsrain" -> ctx.getString(R.string.weather_ts)
        else -> ctx.getString(R.string.weather_unknown)
    }

    fun formatDate(ctx: Context, yyyymmdd: Int): String {
        val s = yyyymmdd.toString()
        val y = s.substring(0, 4).toInt()
        val m = s.substring(4, 6).toInt()
        val d = s.substring(6, 8).toInt()
        val ld = LocalDate.of(y, m, d)
        return ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

    @ColorRes
    fun tempColorRes(tMax: Int): Int = when {
        tMax <= 0 -> R.color.tempCold
        tMax in 1..10 -> R.color.tempCool
        tMax in 11..20 -> R.color.tempMild
        tMax in 21..30 -> R.color.tempWarm
        else -> R.color.tempHot
    }

}