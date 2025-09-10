package com.example.topacademy_android.feature.calculator.presentation.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.topacademy_android.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CalculatorActivity : AppCompatActivity() {

    private val vm: com.example.topacademy_android.feature.calculator.presentation.viewmodel.CalculatorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }


        val tvOperation = findViewById<TextView>(R.id.operation)
        val tvResult    = findViewById<TextView>(R.id.result)


        lifecycleScope.launch {
            vm.state.collectLatest { st ->
                tvOperation.text = st.expression
                tvResult.text = st.error ?: st.result

            }
        }


        fun btn(id: Int) = findViewById<TextView>(id)


        listOf(
            R.id.b_zero, R.id.b_zero3, R.id.b_one, R.id.b_two, R.id.b_three,
            R.id.b_four, R.id.b_five, R.id.b_six, R.id.b_seven, R.id.b_eight, R.id.b_nine
        ).forEach { rid ->
            btn(rid).setOnClickListener { vm.onInput(btn(rid).text.toString()) }
        }


        btn(R.id.b_point).setOnClickListener { vm.onInput(".") }


        btn(R.id.b_addition).setOnClickListener { vm.onInput("+") }
        btn(R.id.b_minus).setOnClickListener   { vm.onInput("-") }
        btn(R.id.b_multiply).setOnClickListener{ vm.onInput("*") }
        btn(R.id.b_rightb).setOnClickListener  { vm.onInput("/") }
        btn(R.id.b_leftb).setOnClickListener   { vm.onInput("%") }
        btn(R.id.b_bracket1).setOnClickListener{ vm.onInput("(") }
        btn(R.id.b_bracket2).setOnClickListener{ vm.onInput(")") }


        btn(R.id.b_sin).setOnClickListener { vm.onInput("sin(") }
        btn(R.id.b_cos).setOnClickListener { vm.onInput("cos(") }
        btn(R.id.b_sqrt).setOnClickListener{ vm.onInput("sqrt(") }
        btn(R.id.b_ln).setOnClickListener  { vm.onInput("ln(") }
        btn(R.id.b_log).setOnClickListener { vm.onInput("log(") }
        btn(R.id.b_pi).setOnClickListener  { vm.onInput("π") }
        btn(R.id.b_e).setOnClickListener   { vm.onInput("e") }
        btn(R.id.b_xy).setOnClickListener  { vm.onInput("^") }


        btn(R.id.b_log2).setOnClickListener { vm.onClear() }

        btn(R.id.b_ln).setOnClickListener { vm.onBackspace() }
        btn(R.id.b_ln).setOnLongClickListener { vm.onClear(); true }

        btn(R.id.b_in).setOnClickListener { vm.onInput("ln(") }

       
        btn(R.id.b_equally).setOnClickListener { vm.onEquals() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}