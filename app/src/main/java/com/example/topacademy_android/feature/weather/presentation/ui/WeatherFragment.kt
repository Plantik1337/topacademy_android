package com.example.topacademy_android.feature.weather.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentWeatherBinding
import com.example.topacademy_android.feature.weather.data.remote.dto.SevenTimerDay
import com.example.topacademy_android.feature.weather.presentation.adapter.WeatherAdapter
import com.example.topacademy_android.feature.weather.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class WeatherFragment : Fragment() {

    private var _b: FragmentWeatherBinding? = null
    private val b get() = _b!!
    private val vm: WeatherViewModel by viewModel()

    private val adapter = WeatherAdapter { openDetails(it) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentWeatherBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.weatherRecycler.layoutManager = LinearLayoutManager(requireContext())
        b.weatherRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { st ->
                    b.progress.isVisible = st.isLoading
                    b.errorText.isVisible = st.error != null
                    adapter.submitList(st.items)
                    val today = st.items.firstOrNull()
                    if (today != null) {
                        b.headerDay.text = WeatherUi.formatDate(requireContext(), today.date)
                        b.headerCity.text = getString(R.string.city_country).uppercase(Locale.getDefault())
                        b.headerTemp.text = getString(R.string.temp_single_format, today.temp2m.max)
                        b.headerIcon.setImageResource(WeatherUi.iconFor(today.weather))
                    }
                }
            }
        }

        vm.load(lat = 40.789, lon = 43.847)
    }

    private fun openDetails(day: SevenTimerDay) {
        val args = Bundle().apply {
            putInt("date", day.date)
            putString("code", day.weather)
            putInt("tmax", day.temp2m.max)
            putInt("tmin", day.temp2m.min)
            putInt("windmax", day.wind10m_max)
        }
        findNavController().navigate(R.id.action_weather_to_detail, args)
    }

    override fun onDestroyView() {
        _b = null
        super.onDestroyView()
    }
}