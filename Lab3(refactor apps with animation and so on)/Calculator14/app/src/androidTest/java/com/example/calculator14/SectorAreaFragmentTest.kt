package com.example.calculator14

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SectorAreaFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testCalculateSectorArea() {
        // Переход на фрагмент "Площадь сектора"
        onView(withId(R.id.nav_sector_area)).perform(click())

        // Ввод данных
        onView(withId(R.id.editTextL)).perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.id.editTextA)).perform(typeText("90"), closeSoftKeyboard())

        // Нажатие на кнопку "Вычислить"
        onView(withId(R.id.buttonCalculate)).perform(click())

        // Проверка результата
        onView(withId(R.id.textViewResult)).check(matches(withText("Площадь сектора: 7.853981633974483")))
    }

    @Test
    fun testInvalidInput() {
        onView(withId(R.id.nav_sector_area)).perform(click())
        onView(withId(R.id.editTextL)).perform(typeText("abc"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())

        onView(withId(R.id.textViewResult)).check(matches(withText("Результат")))
    }
}
