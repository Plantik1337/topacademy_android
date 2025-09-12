package com.example.topacademy_android.di

import com.example.topacademy_android.core.network.RetrofitClient
import com.example.topacademy_android.feature.weather.data.remote.WeatherApi
import com.example.topacademy_android.feature.weather.data.repository.WeatherRepository
import com.example.topacademy_android.feature.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    single<WeatherApi> { RetrofitClient.api }
    single { WeatherRepository() }
    viewModel { WeatherViewModel(get()) }
}