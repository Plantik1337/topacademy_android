package com.example.topacademy_android.features.carlist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.features.carlist.domain.model.Car
import java.text.NumberFormat
import java.util.Locale

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarImage: ImageView = itemView.findViewById(R.id.ivCarImage)
        val tvCarBrand: TextView = itemView.findViewById(R.id.tvCarBrand)
        val tvCarModel: TextView = itemView.findViewById(R.id.tvCarModel)
        val tvCarYear: TextView = itemView.findViewById(R.id.tvCarYear)
        val tvCarDescription: TextView = itemView.findViewById(R.id.tvCarDescription)
        val tvCarCost: TextView = itemView.findViewById(R.id.tvCarCost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        
        holder.ivCarImage.setImageResource(car.imageResId)
        holder.tvCarBrand.text = car.brand
        holder.tvCarModel.text = car.model
        holder.tvCarYear.text = car.year.toString()
        holder.tvCarDescription.text = car.description
        
        val formattedCost = NumberFormat.getCurrencyInstance(Locale.US).format(car.cost)
        holder.tvCarCost.text = formattedCost
    }

    override fun getItemCount(): Int = cars.size
} 