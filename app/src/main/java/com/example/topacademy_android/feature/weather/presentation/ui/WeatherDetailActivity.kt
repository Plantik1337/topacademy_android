package com.example.topacademy_android.feature.weather.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityWeatherDetailBinding

class WeatherDetailActivity : AppCompatActivity() {

    private lateinit var b: ActivityWeatherDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWeatherDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        setSupportActionBar(b.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        b.toolbarDetail.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val date = intent.getIntExtra("date", 0)
        val code = intent.getStringExtra("code") ?: "unknown"
        val tmax = intent.getIntExtra("tmax", 0)
        val tmin = intent.getIntExtra("tmin", 0)
        val wind = intent.getIntExtra("windmax", 0)

        b.detailIcon.setImageResource(WeatherUi.iconFor(code))
        b.detailDesc.text = WeatherUi.textFor(this, code)
        b.detailDate.text = WeatherUi.formatDate(this, date)


        b.detailTempBig.text = getString(R.string.temp_single_format, tmax)


        b.detailTempRange.text = getString(R.string.temp_format, tmax, tmin)
        b.detailWind.text = getString(R.string.wind_format, wind)


    }
}