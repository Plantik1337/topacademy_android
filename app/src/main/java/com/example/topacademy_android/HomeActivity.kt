package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityHomeBinding
import com.example.topacademy_android.features.calculator.presentation.ui.CalculatorActivity
import com.example.topacademy_android.features.carlist.presentation.ui.ListActivity
import com.example.topacademy_android.features.weather.presentation.ui.WeatherActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        binding.btnWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
    }
} 