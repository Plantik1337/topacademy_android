package com.example.topacademy_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    
    private val repository = CalculatorRepository()
    private val calculateExpressionUseCase = CalculateExpressionUseCase(repository)
    
    private val _currentExpression = MutableLiveData<String>("")
    val currentExpression: LiveData<String> = _currentExpression
    
    private val _result = MutableLiveData<String>("0")
    val result: LiveData<String> = _result
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private var isLastInputOperator = false
    private var hasResult = false
    
    fun inputNumber(number: String) {
        if (hasResult) {
            clearAll()
            hasResult = false
        }
        
        val current = _currentExpression.value ?: ""
        _currentExpression.value = current + number
        isLastInputOperator = false
        _error.value = null
    }
    
    fun inputOperator(operator: String) {
        val current = _currentExpression.value ?: ""
        
        if (current.isEmpty()) return
        
        if (isLastInputOperator) {
            // Заменяем последний оператор
            _currentExpression.value = current.dropLast(1) + operator
        } else {
            _currentExpression.value = current + operator
        }
        
        isLastInputOperator = true
        hasResult = false
        _error.value = null
    }
    
    fun inputDecimal() {
        if (hasResult) {
            clearAll()
            hasResult = false
        }
        
        val current = _currentExpression.value ?: ""
        
        // Проверяем, есть ли уже десятичная точка в последнем числе
        val lastNumberStart = current.lastIndexOfAny(charArrayOf('+', '-', '×', '÷')) + 1
        val lastNumber = current.substring(lastNumberStart)
        
        if (!lastNumber.contains(",")) {
            if (current.isEmpty() || isLastInputOperator) {
                _currentExpression.value = current + "0,"
            } else {
                _currentExpression.value = current + ","
            }
            isLastInputOperator = false
        }
        _error.value = null
    }
    
    fun toggleSign() {
        val current = _currentExpression.value ?: ""
        if (current.isNotEmpty() && !isLastInputOperator) {
            // Простая реализация смены знака для последнего числа
            val lastOperatorIndex = current.lastIndexOfAny(charArrayOf('+', '-', '×', '÷'))
            if (lastOperatorIndex == -1) {
                // Только одно число
                if (current.startsWith("-")) {
                    _currentExpression.value = current.drop(1)
                } else {
                    _currentExpression.value = "-$current"
                }
            }
        }
    }
    
    fun deleteLastCharacter() {
        val current = _currentExpression.value ?: ""
        if (current.isNotEmpty()) {
            _currentExpression.value = current.dropLast(1)
            isLastInputOperator = current.last() in "+-×÷"
            hasResult = false
        }
        _error.value = null
    }
    
    fun clearAll() {
        _currentExpression.value = ""
        _result.value = "0"
        _error.value = null
        isLastInputOperator = false
        hasResult = false
    }
    
    fun calculate() {
        val expression = _currentExpression.value ?: ""
        
        if (expression.isBlank()) {
            _error.value = "calc_error_empty_expression"
            return
        }
        
        if (isLastInputOperator) {
            _error.value = "calc_error_incomplete_expression"
            return
        }
        
        calculateExpressionUseCase.execute(expression).fold(
            onSuccess = { result ->
                _result.value = result
                _error.value = null
                hasResult = true
            },
            onFailure = { exception ->
                _error.value = "calc_error_calculation"
            }
        )
    }
} 