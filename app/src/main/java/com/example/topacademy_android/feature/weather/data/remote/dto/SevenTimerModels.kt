
package com.example.topacademy_android.feature.weather.data.remote.dto
import com.google.gson.annotations.SerializedName


data class SevenTimerResponse(
    val product: String?,
    val init: String?,
    val dataseries: List<DataSeries>
)

data class DataSeries(
    val date: Int,
    val weather: String,
    val temp2m: Temp2mRaw,
    val rh2m: String?,
    val cloudcover: String?,
    @SerializedName("wind10m_max")
    val wind10mMax: String?
)


data class Temp2mRaw(
    val max: String?,
    val min: String?
)

data class Temp2m(
    val max: Int,
    val min: Int
)

data class SevenTimerDay(
    val date: Int,
    val weather: String,
    val temp2m: Temp2m,
    val wind10m_max: Int
)