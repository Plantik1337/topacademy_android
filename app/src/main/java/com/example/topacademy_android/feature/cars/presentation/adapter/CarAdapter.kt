package com.example.topacademy_android.feature.cars.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.feature.cars.domain.model.Car
import java.text.NumberFormat
import java.util.Locale

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {


    private val ruNumberFormat = NumberFormat.getNumberInstance(Locale("ru", "RU"))

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewCar: ImageView = itemView.findViewById(R.id.imageViewCar)
        val textViewBrandModel: TextView = itemView.findViewById(R.id.textViewBrandModel)
        val textViewYearCost: TextView = itemView.findViewById(R.id.textViewYearCost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cars_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        val ctx = holder.itemView.context

        holder.imageViewCar.setImageResource(car.imageResId)


        holder.textViewBrandModel.text =
            ctx.getString(R.string.car_brand_model_format, car.brand, car.model)


        val yearText = ctx.resources.getQuantityString(R.plurals.cars_years, car.year, car.year)
        val costText = ruNumberFormat.format(car.cost)
        holder.textViewYearCost.text =
            ctx.getString(R.string.car_year_cost_format, yearText, costText)
    }

    override fun getItemCount(): Int = cars.size
}