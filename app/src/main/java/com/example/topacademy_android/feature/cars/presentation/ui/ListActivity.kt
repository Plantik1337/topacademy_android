package com.example.topacademy_android.feature.cars.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.feature.cars.domain.model.Car
import com.example.topacademy_android.feature.cars.presentation.adapter.CarAdapter

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cars_list)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val cars = listOf(
            Car("Toyota", "Camry", 2020, "Надёжный седан", 2000000, R.drawable.car_image_1),
            Car("BMW", "X5", 2021, "Премиальный кроссовер", 5000000, R.drawable.car_image_2),
            Car(
                "Dodge",
                "Challenger",
                2019,
                "Стильный американский мускул-кар",
                3000000,
                R.drawable.car_image_4
            ),
            Car("Lada", "Vesta", 2019, "Доступный автомобиль", 1000000, R.drawable.car_image_3)
        )


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCars)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarAdapter(cars)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}