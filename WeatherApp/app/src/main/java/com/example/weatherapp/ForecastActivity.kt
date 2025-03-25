package com.example.weatherapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val cityName = intent.getStringExtra("cityName") // Исправлен ключ
        val forecastList = intent.getStringArrayListExtra("forecastList") // Исправлен ключ

        val cityTextView = findViewById<TextView>(R.id.textViewCity)
        val forecastTextView = findViewById<TextView>(R.id.textViewForecast)

        cityTextView.text = "Forecast for: $cityName"
        forecastTextView.text = forecastList?.joinToString("\n") ?: "No forecast available"
    }
}
