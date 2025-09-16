package com.example.topacademy_android.feature.calculator.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {

    private var _b: FragmentCalculatorBinding? = null
    private val b get() = _b!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentCalculatorBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        b.operation.text = ""
        b.result.text = ""

        fun append(text: String) {
            b.operation.append(text)
        }

        fun backspace() {
            val s = b.operation.text?.toString().orEmpty()
            if (s.isNotEmpty()) b.operation.text = s.dropLast(1)
        }

        fun clearAll() {
            b.operation.text = ""
            b.result.text = ""
        }

        fun eval() {
            val raw = b.operation.text?.toString().orEmpty()
            if (raw.isBlank()) return


            var expr = raw

            expr = expr.replace("%", "*0.01")

            expr = expr.replace("×", "*").replace("÷", "/")

            try {
                val result = ExpressionBuilder(expr)
                    .build()
                    .evaluate()

                val text = if (result % 1.0 == 0.0) result.toLong().toString() else result.toString()
                b.result.text = text
            } catch (e: Exception) {
                b.result.text = getString(R.string.calc_error)
            }
        }


        b.bSqrt.setOnClickListener { append("sqrt(") }
        b.bLog.setOnClickListener { append("log(") }
        b.bIn.setOnClickListener  { append("ln(") }


        b.bBracket1.setOnClickListener { append("(") }
        b.bBracket2.setOnClickListener { append(")") }


        b.bXy.setOnClickListener    { append("^") }
        b.bLog2.setOnClickListener  { clearAll() }
        b.bLn.setOnClickListener    { backspace() }
        b.bLeftb.setOnClickListener { append("%") }
        b.bRightb.setOnClickListener{ append("/") }


        b.bSin.setOnClickListener { append("sin(") }
        b.bSeven.setOnClickListener { append("7") }
        b.bEight.setOnClickListener { append("8") }
        b.bNine.setOnClickListener { append("9") }
        b.bMultiply.setOnClickListener { append("*") }


        b.bCos.setOnClickListener { append("cos(") }
        b.bFour.setOnClickListener { append("4") }
        b.bFive.setOnClickListener { append("5") }
        b.bSix.setOnClickListener { append("6") }
        b.bMinus.setOnClickListener { append("-") }


        b.bPi.setOnClickListener { append("pi") }
        b.bOne.setOnClickListener { append("1") }
        b.bTwo.setOnClickListener { append("2") }
        b.bThree.setOnClickListener { append("3") }
        b.bAddition.setOnClickListener { append("+") }


        b.bE.setOnClickListener { append("e") }
        b.bZero3.setOnClickListener { append("00") }
        b.bPoint.setOnClickListener { append(".") }
        b.bZero.setOnClickListener { append("0") }
        b.bEqually.setOnClickListener { eval() }
    }

    override fun onDestroyView() {
        _b = null
        super.onDestroyView()
    }
}