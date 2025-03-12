package com.example.calculator14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.animation.ScaleAnimation


class CalculatorFragment : Fragment(), View.OnClickListener {

    private lateinit var display: TextView
    private var input = StringBuilder()
    private var operator: String? = null
    private var firstOperand: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        display = view.findViewById(R.id.display)

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonDot, R.id.buttonClear,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
            R.id.buttonDivide, R.id.buttonEquals
        )

        buttonIds.forEach { id ->
            view.findViewById<Button>(id).setOnClickListener(this)
        }

        return view
    }

    override fun onClick(v: View) {
        val button = v as Button
        animateButton(button) // Вызов анимации при нажатии

        when (button.id) {
            R.id.buttonClear -> {
                input.clear()
                firstOperand = null
                operator = null
                display.text = ""
            }
            R.id.buttonEquals -> calculateResult()
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide -> {
                operator = button.text.toString()
                firstOperand = input.toString().toDoubleOrNull()
                input.clear()
            }
            else -> {
                input.append(button.text)
                display.text = input.toString()
            }
        }
    }

    private fun animateButton(button: Button) {
        val scaleAnimation = ScaleAnimation(
            1.0f, 0.9f,  // Уменьшение по X
            1.0f, 0.9f,  // Уменьшение по Y
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 150 // Длительность анимации
        scaleAnimation.fillAfter = true
        button.startAnimation(scaleAnimation)
    }

    private fun calculateResult() {
        val secondOperand = input.toString().toDoubleOrNull()
        if (firstOperand == null || secondOperand == null || operator == null) return

        val result = when (operator) {
            "+" -> firstOperand!! + secondOperand
            "-" -> firstOperand!! - secondOperand
            "*" -> firstOperand!! * secondOperand
            "/" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else "Ошибка"
            else -> null
        }

        display.text = result?.toString() ?: "Ошибка"
        input.clear()
        firstOperand = null
        operator = null
    }
}
