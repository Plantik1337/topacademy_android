package com.example.topacademy_android.features.carlist.domain.model

data class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val description: String,
    val cost: Int,
    val imageResId: Int
) 