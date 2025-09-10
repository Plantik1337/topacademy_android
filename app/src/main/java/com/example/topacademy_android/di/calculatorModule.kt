package com.example.topacademy_android.di

import com.example.topacademy_android.feature.calculator.data.CalculatorEvaluator
import com.example.topacademy_android.feature.calculator.domain.usecase.EvaluateExpressionUseCase
import com.example.topacademy_android.feature.calculator.presentation.viewmodel.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calculatorModule = module {
    single { CalculatorEvaluator() }
    single { EvaluateExpressionUseCase(get()) }
    viewModel { CalculatorViewModel(get()) }
}