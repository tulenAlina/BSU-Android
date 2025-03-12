package com.example.calculator14

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.PI
import kotlin.math.pow

class CalculatorTest {

    private fun calculate(first: Double, second: Double, operator: String): Double {
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> if (second != 0.0) first / second else Double.NaN
            else -> Double.NaN
        }
    }

    @Test
    fun testAddition() {
        val result = calculate(2.0, 3.0, "+")
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun testDivisionByZero() {
        val result = calculate(10.0, 0.0, "/")
        assertEquals(Double.NaN, result, 0.001)
    }
}
