package com.example.topacademy_android.features.calculator.data.repository

import com.example.topacademy_android.features.calculator.domain.repository.CalculatorRepository
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorRepositoryImpl : CalculatorRepository {
    
    override fun evaluateExpression(expression: String): Result<Double> {
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
    
    override fun formatResult(result: Double): String {
        return if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString().replace(".", ",")
        }
    }
} 