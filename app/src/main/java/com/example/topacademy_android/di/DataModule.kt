package com.example.topacademy_android.di

import com.example.topacademy_android.features.calculator.data.repository.CalculatorRepositoryImpl
import com.example.topacademy_android.features.calculator.domain.repository.CalculatorRepository
import com.example.topacademy_android.features.carlist.data.repository.CarRepositoryImpl
import com.example.topacademy_android.features.carlist.domain.repository.CarRepository
import com.example.topacademy_android.features.weather.data.remote.WeatherApiService
import com.example.topacademy_android.features.weather.data.repository.WeatherRepositoryImpl
import com.example.topacademy_android.features.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    
    // Weather
    single { WeatherApiService.create() }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    
    // Calculator
    single<CalculatorRepository> { CalculatorRepositoryImpl() }
    
    // Car List
    single<CarRepository> { CarRepositoryImpl() }
} 