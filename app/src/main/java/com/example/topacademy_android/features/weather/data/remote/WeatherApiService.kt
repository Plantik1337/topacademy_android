package com.example.topacademy_android.features.weather.data.remote

import com.example.topacademy_android.features.weather.data.remote.dto.WeatherResponseDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "ru"
    ): Response<WeatherResponseDto>
    
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "21ad60596b7796c739942bd8892de548"
        
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            
            return retrofit.create(WeatherApiService::class.java)
        }
    }
} 