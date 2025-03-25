package com.example.weatherapp

data class ForecastResponse(
    val list: List<Forecast>
)

data class Forecast(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)
