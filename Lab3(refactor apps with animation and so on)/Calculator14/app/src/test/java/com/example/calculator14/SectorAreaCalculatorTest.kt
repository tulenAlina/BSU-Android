package com.example.calculator14

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.PI
import kotlin.math.pow

class SectorAreaCalculatorTest {

    private fun calculateSectorArea(L: Double, A: Double): Double {
        if (A == 0.0) return Double.NaN
        val radius = L / (A * PI / 180)
        return 0.5 * radius.pow(2) * A * PI / 180
    }

    @Test
    fun testValidSectorArea() {
        val result = calculateSectorArea(10.0, 90.0)
        assertEquals(7.853, result, 0.001)
    }

    @Test
    fun testZeroAngle() {
        val result = calculateSectorArea(10.0, 0.0)
        assertEquals(Double.NaN, result, 0.001)
    }
}
