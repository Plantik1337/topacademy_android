package com.example.topacademy_android.features.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.features.weather.domain.model.WeatherItem
import com.example.topacademy_android.features.weather.presentation.helper.WeatherUIHelper
import java.text.SimpleDateFormat
import java.util.*

class WeatherForecastAdapter(
    private var forecastItems: List<WeatherItem>,
    private val onItemClick: (WeatherItem) -> Unit
) : RecyclerView.Adapter<WeatherForecastAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weatherBackground: FrameLayout = itemView.findViewById(R.id.weatherBackground)
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val ivWeatherIcon: ImageView = itemView.findViewById(R.id.ivWeatherIcon)
        val tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvHumidity: TextView = itemView.findViewById(R.id.tvHumidity)
        val tvWind: TextView = itemView.findViewById(R.id.tvWind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_forecast, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = forecastItems[position]
        val context = holder.itemView.context
        
        // Format date and time
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = item.dateTime * 1000
        
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        
        val today = Calendar.getInstance()
        val tomorrow = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
        
        val dayText = when {
            isSameDay(calendar, today) -> context.getString(R.string.weather_today)
            isSameDay(calendar, tomorrow) -> context.getString(R.string.weather_tomorrow)
            else -> dateFormat.format(calendar.time)
        }
        
        holder.tvDay.text = dayText
        holder.tvTime.text = timeFormat.format(calendar.time)
        holder.tvTemperature.text = "${item.temperature}°"
        holder.tvDescription.text = item.description
        holder.tvHumidity.text = "${item.humidity}%"
        holder.tvWind.text = String.format("%.1fм/с", item.windSpeed)
        
        // Set background based on weather type
        val backgroundResource = WeatherUIHelper.getBackgroundResource(item.weatherType)
        holder.weatherBackground.background = ContextCompat.getDrawable(context, backgroundResource)
        
        // Set weather icon
        val iconResource = WeatherUIHelper.getWeatherIcon(item.weatherType)
        holder.ivWeatherIcon.setImageResource(iconResource)
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = forecastItems.size

    fun updateData(newForecastItems: List<WeatherItem>) {
        forecastItems = newForecastItems
        notifyDataSetChanged()
    }

    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }
} 