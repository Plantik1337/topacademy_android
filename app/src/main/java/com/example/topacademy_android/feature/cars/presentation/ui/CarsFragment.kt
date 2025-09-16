package com.example.topacademy_android.feature.cars.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentCarsBinding
import com.example.topacademy_android.feature.cars.domain.model.Car
import com.example.topacademy_android.feature.cars.presentation.adapter.CarAdapter

class CarsFragment : Fragment() {

    private var _b: FragmentCarsBinding? = null
    private val b get() = _b!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentCarsBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.carsRecycler.layoutManager = LinearLayoutManager(requireContext())
        b.carsRecycler.adapter = CarAdapter(demoCars())


        val spacing = resources.getDimensionPixelSize(R.dimen._8dp)

        }
    private fun demoCars(): List<Car> = listOf(
        Car(
            brand = "Toyota",
            model = "Camry",
            year = 2020,
            description = "Надёжный седан",
            cost = 2_100_000,
            imageResId = R.drawable.car_image_1
        ),
        Car(
            brand = "BMW",
            model = "3 Series",
            year = 2019,
            description = "Драйверский характер",
            cost = 3_300_000,
            imageResId = R.drawable.car_image_2
        ),
        Car(
            brand = "Audi",
            model = "A4",
            year = 2018,
            description = "Комфорт и технологии",
            cost = 2_900_000,
            imageResId = R.drawable.car_image_3
        ),
        Car(
            brand = "Kia",
            model = "K5",
            year = 2021,
            description = "Современный дизайн",
            cost = 2_200_000,
            imageResId = R.drawable.car_image_4
        )
    )

    override fun onDestroyView() {
        _b = null
        super.onDestroyView()
    }
}