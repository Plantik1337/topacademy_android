package com.example.topacademy_android.features.calculator.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityCalculatorBinding
import com.example.topacademy_android.features.calculator.presentation.viewmodel.CalculatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupObservers() {
        viewModel.currentExpression.observe(this) { expression ->
            binding.tvHistory.text = expression
        }
        
        viewModel.result.observe(this) { result ->
            binding.tvResult.text = result
        }
        
        viewModel.error.observe(this) { error ->
            error?.let {
                val message = when (it) {
                    "calc_error_empty_expression" -> getString(R.string.calc_error_empty_expression)
                    "calc_error_incomplete_expression" -> getString(R.string.calc_error_incomplete_expression)
                    "calc_error_calculation" -> getString(R.string.calc_error_calculation)
                    else -> it
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btn0.setOnClickListener { viewModel.inputNumber("0") }
        binding.btn1.setOnClickListener { viewModel.inputNumber("1") }
        binding.btn2.setOnClickListener { viewModel.inputNumber("2") }
        binding.btn3.setOnClickListener { viewModel.inputNumber("3") }
        binding.btn4.setOnClickListener { viewModel.inputNumber("4") }
        binding.btn5.setOnClickListener { viewModel.inputNumber("5") }
        binding.btn6.setOnClickListener { viewModel.inputNumber("6") }
        binding.btn7.setOnClickListener { viewModel.inputNumber("7") }
        binding.btn8.setOnClickListener { viewModel.inputNumber("8") }
        binding.btn9.setOnClickListener { viewModel.inputNumber("9") }
        
        binding.btnPlus.setOnClickListener { viewModel.inputOperator("+") }
        binding.btnMinus.setOnClickListener { viewModel.inputOperator("-") }
        binding.btnMultiply.setOnClickListener { viewModel.inputOperator("×") }
        binding.btnDivide.setOnClickListener { viewModel.inputOperator("÷") }
        
        binding.btnDot.setOnClickListener { viewModel.inputDecimal() }
        binding.btnPlusMinus.setOnClickListener { viewModel.toggleSign() }
        binding.btnC.setOnClickListener { viewModel.clearAll() }
        binding.btnDel.setOnClickListener { viewModel.deleteLastCharacter() }
        binding.btnEquals.setOnClickListener { viewModel.calculate() }
        
        binding.btnPercent.setOnClickListener {
            Toast.makeText(this, getString(R.string.calc_percent_not_implemented), Toast.LENGTH_SHORT).show()
        }
    }
} 