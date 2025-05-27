package com.example.topacademy_android.features.carlist.domain.usecase

import com.example.topacademy_android.features.carlist.domain.model.Car
import com.example.topacademy_android.features.carlist.domain.repository.CarRepository

class GetCarsUseCase(
    private val repository: CarRepository
) {
    fun execute(): List<Car> {
        return repository.getCars()
    }
} 