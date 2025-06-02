package com.example.topacademy_android.features.carlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topacademy_android.features.carlist.domain.model.Car
import com.example.topacademy_android.features.carlist.domain.usecase.GetCarsUseCase

class CarListViewModel(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {
    
    private val _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> = _cars
    
    init {
        loadCars()
    }
    
    private fun loadCars() {
        _cars.value = getCarsUseCase.execute()
    }
} 