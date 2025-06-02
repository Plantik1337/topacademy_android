package com.example.topacademy_android.features.carlist.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.features.carlist.presentation.adapter.CarAdapter
import com.example.topacademy_android.features.carlist.presentation.viewmodel.CarListViewModel
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {
    
    private lateinit var rvCars: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private val viewModel: CarListViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
    }
    
    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        rvCars = findViewById(R.id.rvCars)
        rvCars.layoutManager = LinearLayoutManager(this)
    }
    
    private fun setupObservers() {
        viewModel.cars.observe(this) { cars ->
            val adapter = CarAdapter(cars)
            rvCars.adapter = adapter
        }
    }
} 