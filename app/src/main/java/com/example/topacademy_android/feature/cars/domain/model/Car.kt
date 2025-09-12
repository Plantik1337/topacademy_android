package com.example.topacademy_android.feature.cars.domain.model

import androidx.annotation.DrawableRes

data class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val description: String,
    val cost: Int,
    @DrawableRes val imageResId: Int
)