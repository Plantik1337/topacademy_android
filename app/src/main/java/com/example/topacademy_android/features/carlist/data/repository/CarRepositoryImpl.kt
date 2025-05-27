package com.example.topacademy_android.features.carlist.data.repository

import com.example.topacademy_android.R
import com.example.topacademy_android.features.carlist.domain.model.Car
import com.example.topacademy_android.features.carlist.domain.repository.CarRepository

class CarRepositoryImpl : CarRepository {
    
    override fun getCars(): List<Car> {
        return listOf(
            Car(
                brand = "BMW",
                model = "X5",
                year = 2022,
                description = "Luxury SUV with premium features and advanced technology",
                cost = 75000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Mercedes-Benz",
                model = "C-Class",
                year = 2021,
                description = "Elegant sedan with sophisticated design and comfort",
                cost = 55000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Audi",
                model = "A4",
                year = 2023,
                description = "Sporty sedan with cutting-edge technology and performance",
                cost = 45000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Tesla",
                model = "Model 3",
                year = 2022,
                description = "Electric vehicle with autopilot and sustainable energy",
                cost = 50000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Toyota",
                model = "Camry",
                year = 2021,
                description = "Reliable and fuel-efficient family sedan",
                cost = 35000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Honda",
                model = "Civic",
                year = 2022,
                description = "Compact car with excellent fuel economy and reliability",
                cost = 28000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Ford",
                model = "Mustang",
                year = 2023,
                description = "Iconic American muscle car with powerful performance",
                cost = 42000,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            Car(
                brand = "Volkswagen",
                model = "Golf",
                year = 2021,
                description = "Compact hatchback with European engineering excellence",
                cost = 32000,
                imageResId = R.drawable.ic_launcher_foreground
            )
        )
    }
} 