package com.example.topacademy_android

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailActivity : AppCompatActivity() {
    
    private lateinit var toolbar: MaterialToolbar
    private lateinit var detailWeatherBackground: FrameLayout
    private lateinit var tvDetailDate: TextView
    private lateinit var tvDetailTemperature: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var ivDetailWeatherIcon: ImageView
    private lateinit var tvDetailFeelsLike: TextView
    private lateinit var tvDetailHumidity: TextView
    private lateinit var tvDetailWind: TextView
    private lateinit var tvDetailPrecipitation: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        
        setupViews()
        setupToolbar()
        loadWeatherDetails()
    }
    
    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        detailWeatherBackground = findViewById(R.id.detailWeatherBackground)
        tvDetailDate = findViewById(R.id.tvDetailDate)
        tvDetailTemperature = findViewById(R.id.tvDetailTemperature)
        tvDetailDescription = findViewById(R.id.tvDetailDescription)
        ivDetailWeatherIcon = findViewById(R.id.ivDetailWeatherIcon)
        tvDetailFeelsLike = findViewById(R.id.tvDetailFeelsLike)
        tvDetailHumidity = findViewById(R.id.tvDetailHumidity)
        tvDetailWind = findViewById(R.id.tvDetailWind)
        tvDetailPrecipitation = findViewById(R.id.tvDetailPrecipitation)
    }
    
    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun loadWeatherDetails() {
        // Get data from intent
        val dateTime = intent.getLongExtra("dateTime", 0L)
        val temperature = intent.getIntExtra("temperature", 0)
        val feelsLike = intent.getIntExtra("feelsLike", 0)
        val description = intent.getStringExtra("description") ?: ""
        val humidity = intent.getIntExtra("humidity", 0)
        val windSpeed = intent.getDoubleExtra("windSpeed", 0.0)
        val precipitation = intent.getIntExtra("precipitation", 0)
        val weatherTypeName = intent.getStringExtra("weatherType") ?: "SUNNY"
        
        val weatherType = WeatherType.valueOf(weatherTypeName)
        
        // Format date and time
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateTime * 1000
        
        val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        
        val formattedDate = "${dateFormat.format(calendar.time)}, ${timeFormat.format(calendar.time)}"
        
        // Set data to views
        tvDetailDate.text = formattedDate
        tvDetailTemperature.text = "${temperature}°"
        tvDetailDescription.text = description
        tvDetailFeelsLike.text = "${feelsLike}°"
        tvDetailHumidity.text = "${humidity}%"
        tvDetailWind.text = String.format("%.1f м/с", windSpeed)
        tvDetailPrecipitation.text = "${precipitation}%"
        

        val backgroundResource = WeatherUIHelper.getBackgroundResource(weatherType)
        detailWeatherBackground.background = ContextCompat.getDrawable(this, backgroundResource)
        
        // Set weather icon
        val iconResource = WeatherUIHelper.getWeatherIcon(weatherType)
        ivDetailWeatherIcon.setImageResource(iconResource)
    }
    

} 