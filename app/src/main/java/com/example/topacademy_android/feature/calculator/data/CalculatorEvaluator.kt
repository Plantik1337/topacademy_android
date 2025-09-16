package com.example.topacademy_android.feature.calculator.data

import net.objecthunter.exp4j.ExpressionBuilder


class CalculatorEvaluator {

    fun evaluate(raw: String): Double {
        val expr = normalize(raw)
        val expression = ExpressionBuilder(expr)
            .build()
        return expression.evaluate()
    }

    private fun normalize(s: String): String =
        s.replace(',', '.')
            .replace('×', '*')
            .replace('÷', '/')
            .replace('π', 'p')
            .replace("p", "pi")
}