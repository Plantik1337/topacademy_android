package com.example.topacademy_android.features.calculator.domain.repository

interface CalculatorRepository {
    fun evaluateExpression(expression: String): Result<Double>
    fun formatResult(result: Double): String
} 