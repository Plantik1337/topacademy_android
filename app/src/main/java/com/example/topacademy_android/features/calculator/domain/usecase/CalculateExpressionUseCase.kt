package com.example.topacademy_android.features.calculator.domain.usecase

import com.example.topacademy_android.features.calculator.domain.repository.CalculatorRepository

class CalculateExpressionUseCase(private val repository: CalculatorRepository) {
    
    fun execute(expression: String): Result<String> {
        if (expression.isBlank()) {
            return Result.failure(IllegalArgumentException("Empty expression"))
        }
        
        return repository.evaluateExpression(expression).fold(
            onSuccess = { result ->
                val formattedResult = repository.formatResult(result)
                Result.success(formattedResult)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }
} 