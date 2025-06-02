package com.example.topacademy_android.features.weather.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.features.weather.domain.model.WeatherItem
import com.example.topacademy_android.features.weather.presentation.adapter.WeatherForecastAdapter
import com.example.topacademy_android.features.weather.presentation.helper.WeatherUIHelper
import com.example.topacademy_android.features.weather.presentation.viewmodel.WeatherViewModel
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity() {
    
    private lateinit var toolbar: MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var errorLayout: LinearLayout
    private lateinit var weatherContent: androidx.core.widget.NestedScrollView
    private lateinit var btnRetry: Button
    
    // Current weather views
    private lateinit var tvLocation: TextView
    private lateinit var currentWeatherBackground: FrameLayout
    private lateinit var tvCurrentTemperature: TextView
    private lateinit var tvCurrentDescription: TextView
    private lateinit var ivCurrentWeatherIcon: ImageView
    private lateinit var tvCurrentFeelsLike: TextView
    private lateinit var tvCurrentHumidity: TextView
    
    // Forecast views
    private lateinit var rvWeatherForecast: RecyclerView
    private lateinit var forecastAdapter: WeatherForecastAdapter
    
    private val viewModel: WeatherViewModel by viewModel()
    
    // Moscow coordinates as default
    private val latitude = 55.7558
    private val longitude = 37.6176
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        
        setupViews()
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        loadWeatherData()
    }
    
    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        progressBar = findViewById(R.id.progressBar)
        errorLayout = findViewById(R.id.errorLayout)
        weatherContent = findViewById(R.id.weatherContent)
        btnRetry = findViewById(R.id.btnRetry)
        
        tvLocation = findViewById(R.id.tvLocation)
        currentWeatherBackground = findViewById(R.id.currentWeatherBackground)
        tvCurrentTemperature = findViewById(R.id.tvCurrentTemperature)
        tvCurrentDescription = findViewById(R.id.tvCurrentDescription)
        ivCurrentWeatherIcon = findViewById(R.id.ivCurrentWeatherIcon)
        tvCurrentFeelsLike = findViewById(R.id.tvCurrentFeelsLike)
        tvCurrentHumidity = findViewById(R.id.tvCurrentHumidity)
        
        rvWeatherForecast = findViewById(R.id.rvWeatherForecast)
        
        btnRetry.setOnClickListener {
            loadWeatherData()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        forecastAdapter = WeatherForecastAdapter(emptyList()) { weatherItem ->
            openWeatherDetail(weatherItem)
        }
        
        rvWeatherForecast.apply {
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            adapter = forecastAdapter
        }
    }
    
    private fun setupObservers() {
        viewModel.weatherForecast.observe(this) { forecast ->
            showWeatherData(forecast.cityName, forecast.country, forecast.items)
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                showLoading()
            }
        }
        
        viewModel.error.observe(this) { error ->
            error?.let {
                showError()
            }
        }
    }
    
    private fun loadWeatherData() {
        viewModel.loadWeatherForecast(latitude, longitude)
    }
    
    private fun showWeatherData(cityName: String, country: String, items: List<WeatherItem>) {
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.GONE
        weatherContent.visibility = View.VISIBLE
        
        // Set location
        tvLocation.text = "$cityName, $country"
        
        // Set current weather (first item)
        if (items.isNotEmpty()) {
            val currentWeather = items.first()
            
            tvCurrentTemperature.text = "${currentWeather.temperature}°"
            tvCurrentDescription.text = currentWeather.description
            tvCurrentFeelsLike.text = getString(R.string.weather_feels_like, currentWeather.feelsLike)
            tvCurrentHumidity.text = getString(R.string.weather_humidity, currentWeather.humidity)
            
            // Set background
            val backgroundResource = WeatherUIHelper.getBackgroundResource(currentWeather.weatherType)
            currentWeatherBackground.background = ContextCompat.getDrawable(this, backgroundResource)
            
            // Set icon
            val iconResource = WeatherUIHelper.getWeatherIcon(currentWeather.weatherType)
            ivCurrentWeatherIcon.setImageResource(iconResource)
        }
        
        // Update forecast (take first 15 items)
        forecastAdapter.updateData(items.take(15))
    }
    
    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
        weatherContent.visibility = View.GONE
    }
    
    private fun showError() {
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        weatherContent.visibility = View.GONE
    }
    
    private fun openWeatherDetail(weatherItem: WeatherItem) {
        val intent = Intent(this, WeatherDetailActivity::class.java).apply {
            putExtra("dateTime", weatherItem.dateTime)
            putExtra("temperature", weatherItem.temperature)
            putExtra("feelsLike", weatherItem.feelsLike)
            putExtra("description", weatherItem.description)
            putExtra("humidity", weatherItem.humidity)
            putExtra("windSpeed", weatherItem.windSpeed)
            putExtra("precipitation", weatherItem.precipitationProbability)
            putExtra("weatherType", weatherItem.weatherType.name)
        }
        startActivity(intent)
    }
} 