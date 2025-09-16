package com.example.topacademy_android

import android.app.Application
import com.example.topacademy_android.di.calculatorModule
import com.example.topacademy_android.di.coreModule
import com.example.topacademy_android.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                calculatorModule,
                weatherModule,
            )
        }
    }
}