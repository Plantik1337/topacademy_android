package com.example.topacademy_android.feature.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.topacademy_android.R
import com.example.topacademy_android.feature.calculator.presentation.ui.CalculatorActivity
import com.example.topacademy_android.feature.cars.presentation.ui.ListActivity
import com.example.topacademy_android.feature.weather.presentation.ui.WeatherActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val btnCalculator = findViewById<Button>(R.id.buttonCalculator)
        val btnList = findViewById<Button>(R.id.buttonList)
        val btnWeather = findViewById<Button>(R.id.buttonWeather)

        btnCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        btnWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}