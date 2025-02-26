package com.example.calculator14

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.PI
import kotlin.math.pow

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editTextL: EditText
    private lateinit var editTextA: EditText
    private lateinit var editTextC: EditText
    private lateinit var textViewResult: TextView
    private lateinit var display: TextView

    private var input = StringBuilder()
    private var operator: String? = null
    private var firstOperand: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextL = findViewById(R.id.editTextL)
        editTextA = findViewById(R.id.editTextA)
        editTextC = findViewById(R.id.editTextC)
        textViewResult = findViewById(R.id.textViewResult)
        display = findViewById(R.id.display)

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonDot, R.id.buttonClear,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
            R.id.buttonDivide, R.id.buttonEquals, R.id.buttonCalculate
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        val button = v as Button
        when (button.id) {
            R.id.buttonClear -> {
                input.clear()
                firstOperand = null
                operator = null
                display.text = ""
            }

            R.id.buttonEquals -> calculateResult()
            R.id.buttonCalculate -> calculateSectorArea()
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

    private fun calculateResult() {
        val secondOperand = input.toString().toDoubleOrNull()
        if (firstOperand == null || secondOperand == null || operator == null) return

        val result = when (operator) {
            "+" -> firstOperand!! + secondOperand
            "-" -> firstOperand!! - secondOperand
            "*" -> firstOperand!! * secondOperand
            "/" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else null
            else -> null
        }

        display.text = result?.toString() ?: getString(R.string.error_divide_by_zero)
        input.clear()
        firstOperand = null
        operator = null
    }

    private fun calculateSectorArea() {
        val L = editTextL.text.toString().toDoubleOrNull()
        val A = editTextA.text.toString().toDoubleOrNull()
        val C = editTextC.text.toString().toDoubleOrNull()

        if (L == null || A == null || C == null) {
            Toast.makeText(this, getString(R.string.error_invalid_input), Toast.LENGTH_SHORT).show()
            return
        }

        if (A == 0.0) {
            textViewResult.text = getString(R.string.error_divide_by_zero)
            return
        }

        val radius = L / (A * PI / 180)
        val area = 0.5 * radius.pow(2) * A * PI / 180
        textViewResult.text = String.format(getString(R.string.result), area)
    }
}
