package com.example.topacademy_android.features.carlist.domain.repository

import com.example.topacademy_android.features.carlist.domain.model.Car

interface CarRepository {
    fun getCars(): List<Car>
} 