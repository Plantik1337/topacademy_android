package com.example.topacademy_android.feature.calculator.domain.usecase

import com.example.topacademy_android.feature.calculator.data.CalculatorEvaluator


class EvaluateExpressionUseCase(
    private val evaluator: CalculatorEvaluator
) {
    operator fun invoke(expression: String): Result<Double> =
        runCatching { evaluator.evaluate(expression) }
}