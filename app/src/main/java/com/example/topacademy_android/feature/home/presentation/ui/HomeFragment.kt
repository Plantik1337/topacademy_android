package com.example.topacademy_android.feature.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.btnWeather.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_weather)
        }
        b.btnCalculator.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_calculator)
        }
        b.btnCars.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_cars)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}