package com.example.topacademy_android.feature.weather.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment() {

    private var _b: FragmentWeatherDetailBinding? = null
    private val b get() = _b!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        val date   = args.getInt("date")
        val code   = args.getString("code").orEmpty()
        val tmax   = args.getInt("tmax")
        val tmin   = args.getInt("tmin")
        val wind   = args.getInt("windmax")

        b.detailIcon.setImageResource(WeatherUi.iconFor(code))
        b.detailTempBig.text = getString(R.string.temp_single_format, tmax)
        b.detailDesc.text = WeatherUi.textFor(requireContext(), code)
        b.detailDate.text = WeatherUi.formatDate(requireContext(), date)
        b.detailTempRange.text = getString(R.string.temp_range_format, tmin, tmax)
        b.detailWind.text = getString(R.string.wind_format, wind)
    }

    override fun onDestroyView() {
        _b = null
        super.onDestroyView()
    }
}