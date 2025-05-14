package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        private const val ON_CREATE = "ON_CREATE"
        private const val ON_START = "ON_START"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(ON_CREATE, R.string.activity_created.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.i(ON_START, R.string.activity_created.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.i("ON_RESUME", R.string.activity_created.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.i("ON_PAUSE", R.string.activity_created.toString())
    }

    override fun onStop() {
        super.onStop()
        Log.i("ON_STOP", R.string.activity_created.toString())
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ON_RESTART", R.string.activity_created.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ON_DESTROY", R.string.activity_created.toString())
    }
}