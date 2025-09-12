package com.example.topacademy_android.feature.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentHomeLoginBinding

class HomeLoginFragment : Fragment() {

    private var _binding: FragmentHomeLoginBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeLoginBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeLogin_to_home)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}