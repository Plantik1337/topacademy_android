package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

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
    
    private val weatherApi = WeatherApiService.create()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    
    // Moscow coordinates as default
    private val latitude = 55.7558
    private val longitude = 37.6176
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        
        setupViews()
        setupToolbar()
        setupRecyclerView()
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
    
    private fun loadWeatherData() {
        showLoading()
        
        scope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    weatherApi.getForecast(
                        latitude = latitude,
                        longitude = longitude,
                        apiKey = WeatherApiService.API_KEY
                    )
                }
                
                if (response.isSuccessful && response.body() != null) {
                    val weatherData = response.body()!!
                    val processedData = processWeatherData(weatherData)
                    
                    showWeatherData(weatherData, processedData)
                } else {
                    showError()
                }
                
            } catch (e: Exception) {
                showError()
            }
        }
    }
    
    private fun processWeatherData(weatherResponse: WeatherResponse): List<ProcessedWeatherItem> {
        return weatherResponse.list.take(15).map { item ->
            val weatherType = determineWeatherType(item.weather.firstOrNull()?.main ?: "")
            
            ProcessedWeatherItem(
                dateTime = item.dateTime,
                dateTimeText = item.dateTimeText,
                temperature = item.main.temperature.roundToInt(),
                feelsLike = item.main.feelsLike.roundToInt(),
                description = item.weather.firstOrNull()?.description?.replaceFirstChar { 
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                } ?: "",
                humidity = item.main.humidity,
                windSpeed = item.wind.speed,
                precipitationProbability = (item.precipitationProbability * 100).roundToInt(),
                weatherType = weatherType
            )
        }
    }
    
    private fun determineWeatherType(weatherMain: String): WeatherType {
        return when (weatherMain.lowercase()) {
            "clear" -> WeatherType.SUNNY
            "clouds" -> WeatherType.CLOUDY
            "rain", "drizzle" -> WeatherType.RAINY
            "thunderstorm" -> WeatherType.STORMY
            "snow" -> WeatherType.SNOWY
            "mist", "fog", "haze" -> WeatherType.FOGGY
            else -> WeatherType.CLOUDY
        }
    }
    

    
    private fun showWeatherData(weatherResponse: WeatherResponse, processedData: List<ProcessedWeatherItem>) {
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.GONE
        weatherContent.visibility = View.VISIBLE
        
        // Set location
        tvLocation.text = "${weatherResponse.city.name}, ${weatherResponse.city.country}"
        
        // Set current weather (first item)
        if (processedData.isNotEmpty()) {
            val currentWeather = processedData.first()
            
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
        
        // Update forecast
        forecastAdapter.updateData(processedData)
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
    
    private fun openWeatherDetail(weatherItem: ProcessedWeatherItem) {
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
    

    
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
} 