package com.example.topacademy_android.di

import com.example.topacademy_android.features.calculator.domain.usecase.CalculateExpressionUseCase
import com.example.topacademy_android.features.carlist.domain.usecase.GetCarsUseCase
import com.example.topacademy_android.features.weather.domain.usecase.GetWeatherForecastUseCase
import org.koin.dsl.module

val domainModule = module {
    
    // Calculator Use Cases
    single { CalculateExpressionUseCase(get()) }
    
    // Car List Use Cases
    single { GetCarsUseCase(get()) }
    
    // Weather Use Cases
    single { GetWeatherForecastUseCase(get()) }
} 