package com.example.calculator14

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAddition() {
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        onView(withId(R.id.display)).check(matches(withText("5.0")))
    }

    @Test
    fun testDivisionByZero() {
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.buttonDivide)).perform(click())
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        onView(withId(R.id.display)).check(matches(withText("Ошибка")))
    }

    @Test
    fun testClear() {
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.display)).check(matches(withText("")))
    }
}
