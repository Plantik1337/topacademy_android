package com.example.topacademy_android.feature.weather.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityWeatherBinding
import com.example.topacademy_android.feature.weather.data.remote.dto.SevenTimerDay
import com.example.topacademy_android.feature.weather.data.repository.WeatherRepository
import com.example.topacademy_android.feature.weather.presentation.adapter.WeatherAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.util.Locale

class WeatherActivity : AppCompatActivity() {

    private val weatherRepo: WeatherRepository by inject()
    private lateinit var b: ActivityWeatherBinding
    private val adapter = WeatherAdapter { openDetails(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WeatherActivity", "onCreate start")
        enableEdgeToEdge()
        b = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(b.root)
        Log.d("WeatherActivity", "setContentView ok")

        setSupportActionBar(b.toolbar)
        Log.d("WeatherActivity", "toolbar ok")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        b.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        b.toolbar.subtitle = getString(R.string.city_country)

        b.weatherRecycler.layoutManager = LinearLayoutManager(this)
        b.weatherRecycler.adapter = adapter

        loadForecast()
    }

    private fun loadForecast() {
        b.progress.visibility = View.VISIBLE
        b.errorText.visibility = View.GONE

        lifecycleScope.launch {
            try {


                val list = withContext(Dispatchers.IO) {
                    weatherRepo.getForecast(lat = 40.789, lon = 43.847)
                }

                val today = list.firstOrNull()
                if (today != null) {
                    b.headerDay.text = WeatherUi.formatDate(this@WeatherActivity, today.date)
                    b.headerCity.text = getString(R.string.city_country).uppercase(Locale.getDefault())
                    b.headerTemp.text = getString(R.string.temp_single_format, today.temp2m.max)
                    b.headerIcon.setImageResource(WeatherUi.iconFor(today.weather))
                }

                adapter.submitList(list)
            } catch (e: Exception) {
                b.errorText.visibility = View.VISIBLE
                b.errorText.text = e.localizedMessage ?: "Не удалось загрузить данные"
            } finally {
                b.progress.visibility = View.GONE
            }
        }
    }

    private fun openDetails(day: SevenTimerDay) {
        val i = Intent(this, WeatherDetailActivity::class.java).apply {
            putExtra("date", day.date)
            putExtra("code", day.weather)
            putExtra("tmax", day.temp2m.max)
            putExtra("tmin", day.temp2m.min)
            putExtra("windmax", day.wind10m_max)
        }
        startActivity(i)
    }
}