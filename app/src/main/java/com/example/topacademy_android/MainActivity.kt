package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        private const val ON_CREATE = "ON_CREATE"
    }

    private val tag = "MainActivity_Lifecycle"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onStartTimestamp = System.currentTimeMillis()
        Log.d(tag, "onCreate() вызван")

        onCreateTime = System.currentTimeMillis()

        Log.i(ON_CREATE, "Активити создана!")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart() вызван")

        val diff = System.currentTimeMillis() - onCreateTime
        Log.d(tag, "Разница onCreate→onStart: $diff мс")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume() вызван")

        val diff = System.currentTimeMillis() - onStartTimestamp
        Log.d(tag, "Разница onStart→onResume: $diff мс")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause() вызван")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop() вызван")
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy() вызван")
    }

    private var onCreateTime: Long = 0
    private var onStartTimestamp: Long = 0
}