package com.example.topacademy_android.feature.calculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.feature.calculator.data.CalculatorEvaluator
import com.example.topacademy_android.feature.calculator.domain.usecase.EvaluateExpressionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat

data class CalcUiState(
    val expression: String = "",
    val result: String = "",
    val error: String? = null
)

class CalculatorViewModel(
    private val useCase: EvaluateExpressionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CalcUiState())
    val state: StateFlow<CalcUiState> = _state.asStateFlow()

    private val numberFormat = DecimalFormat("#.########")

    fun onInput(token: String) {
        _state.update { it.copy(expression = it.expression + token, error = null) }
    }

    fun onClear() {
        _state.value = CalcUiState()
    }

    fun onBackspace() {
        _state.update { st ->
            val e = st.expression
            st.copy(expression = if (e.isNotEmpty()) e.dropLast(1) else "", error = null)
        }
    }

    fun onEquals() {
        val expr = _state.value.expression
        if (expr.isBlank()) return
        viewModelScope.launch {
            useCase(expr)
                .onSuccess { value ->
                    _state.update {
                        it.copy(result = numberFormat.format(value), error = null)
                    }
                }
                .onFailure { e ->
                    _state.update {
                        it.copy(result = "", error = e.message ?: "Ошибка выражения")
                    }
                }
        }
    }


    fun setExpression(text: String) {
        _state.update { it.copy(expression = text, error = null) }
    }


    companion object {
        fun factory() = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val evaluator = CalculatorEvaluator()
                val uc = EvaluateExpressionUseCase(evaluator)
                return CalculatorViewModel(uc) as T
            }
        }
    }
}