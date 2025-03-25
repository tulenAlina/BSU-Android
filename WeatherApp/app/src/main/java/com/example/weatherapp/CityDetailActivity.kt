package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.view.inputmethod.InputMethodManager

class CityDetailActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        val cityName = intent.getStringExtra("cityName") ?: return
        val cityTextView: TextView = findViewById(R.id.cityNameTextView)
        val forecastTextView: TextView = findViewById(R.id.forecastTextView)
        val forecastButton: Button = findViewById(R.id.forecastButton)

        cityTextView.text = "Прогноз для: $cityName"

        // Загрузка прогноза на 1 день и отображение
        weatherViewModel.getForecast(cityName, 1) { forecast ->
            lifecycleScope.launch {
                // Отображаем все временные точки прогноза для одного дня
                forecastTextView.text = forecast.joinToString("\n")
            }
        }

        // Обработка нажатия на кнопку для открытия ForecastActivity
        forecastButton.setOnClickListener {
            hideKeyboard() // Скрыть клавиатуру перед переходом
            weatherViewModel.getForecast(cityName, 3) { forecastList ->
                openForecastActivity(cityName, forecastList)
            }
        }
    }

    private fun openForecastActivity(city: String, forecastList: List<String>) {
        val intent = Intent(this, ForecastActivity::class.java).apply {
            putExtra("cityName", city)
            putStringArrayListExtra("forecastList", ArrayList(forecastList))
        }
        startActivity(intent)
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}