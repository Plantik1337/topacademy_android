package com.example.topacademy_android.di

import com.example.topacademy_android.features.calculator.presentation.viewmodel.CalculatorViewModel
import com.example.topacademy_android.features.carlist.presentation.viewmodel.CarListViewModel
import com.example.topacademy_android.features.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    
    // ViewModels
    viewModel { CalculatorViewModel(get()) }
    viewModel { CarListViewModel(get()) }
    viewModel { WeatherViewModel(get()) }
} 