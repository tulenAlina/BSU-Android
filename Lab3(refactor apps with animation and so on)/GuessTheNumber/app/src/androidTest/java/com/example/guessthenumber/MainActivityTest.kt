package com.example.guessthenumber

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Проверка наличия основных элементов
    @Test
    fun checkInitialUIElements() {
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(withText(R.string.try_to_guess)))
        onView(withId(R.id.editText)).check(matches(isDisplayed()))
        onView(withId(R.id.button1)).check(matches(withText(R.string.input_value)))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.button_new_game)).check(matches(isDisplayed()))
        onView(withId(R.id.button_exit)).check(matches(isDisplayed()))
    }

    // Проверка неправильного ввода
    @Test
    fun testInvalidInput() {
        onView(withId(R.id.editText)).perform(typeText("150"), closeSoftKeyboard())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView2)).check(matches(withText(R.string.error)))
    }

    // Проверка уменьшения количества попыток
    @Test
    fun testAttemptsDecrease() {
        onView(withId(R.id.editText)).perform(typeText("50"), closeSoftKeyboard())
        onView(withId(R.id.button1)).perform(click())

        onView(withId(R.id.progressBar)).check { view, _ ->
            val progressBar = view as ProgressBar
            assert(progressBar.progress < 100) // Проверяем, что прогресс уменьшается
        }
    }

    // Проверка работы кнопки "Новая игра"
    @Test
    fun testNewGameButton() {
        onView(withId(R.id.button_new_game)).perform(click())
        onView(withId(R.id.textView2)).check(matches(withText(R.string.try_to_guess)))
    }

    // Проверка работы кнопки "Выход"
    @Test
    fun testExitButton() {
        onView(withId(R.id.button_exit)).perform(click())
    }

    // Проверка появления диалогового окна при проигрыше
    @Test
    fun testLoseDialog() {
        repeat(5) {
            onView(withId(R.id.editText)).perform(typeText("1"), closeSoftKeyboard())
            onView(withId(R.id.button1)).perform(click())
        }
        onView(withText("Вы проиграли!")).check(matches(isDisplayed()))
    }
}
