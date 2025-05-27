package com.example.topacademy_android

import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorRepository {
    
    fun evaluateExpression(expression: String): Result<Double> {
        return try {
            // Заменяем символы на математические операторы для библиотеки
            val processedExpression = expression
                .replace("×", "*")
                .replace("÷", "/")
                .replace(",", ".")
            
            val result = ExpressionBuilder(processedExpression)
                .build()
                .evaluate()
            
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun formatResult(result: Double): String {
        return if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString().replace(".", ",")
        }
    }
} 