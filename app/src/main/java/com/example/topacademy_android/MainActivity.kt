package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LIFECYCLE"
        private const val TIMING = "TIMING"
    }

    private lateinit var binding: ActivityMainBinding

    private var createTime = 0L
    private var startTime = 0L
    private var resumeTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createTime = System.currentTimeMillis()
        Log.i(TAG, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        startTime = System.currentTimeMillis()
        Log.i(TAG, "onStart()")
        Log.d(TIMING, "onStart - onCreate = ${startTime - createTime} ms")
    }

    override fun onResume() {
        super.onResume()
        resumeTime = System.currentTimeMillis()
        Log.i(TAG, "onResume()")
        Log.d(TIMING, "onResume - onStart = ${resumeTime - startTime} ms")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }
}
