package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val apiKey = "a5c73e74a9ac9b5ca7b8c4c242dfef2f" // Твой API-ключ OpenWeatherMap

    // Получение текущей погоды
    fun getWeather(city: String, callback: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.weatherService.getCurrentWeather(city, apiKey)
                if (response.isSuccessful) {
                    val temp = "${response.body()?.main?.temp?.toInt()}°C"
                    callback(temp)
                } else {
                    Log.e("WeatherViewModel", "Ошибка: ${response.code()} ${response.message()}")
                    callback("Ошибка загрузки погоды")
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Исключение при запросе погоды", e)
                callback("Ошибка соединения")
            }
        }
    }

    // Получение прогноза на 3 или 7 дней
    fun getForecast(city: String, days: Int = 1, callback: (List<String>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.weatherService.getWeatherForecast(city, apiKey)
                if (response.isSuccessful) {
                    val forecastList = response.body()?.list ?: emptyList()

                    // Если запрашивается 1 день, выводим все временные точки
                    val forecast = if (days == 1) {
                        forecastList.filter { it.dt_txt.split(" ")[0] == forecastList[0].dt_txt.split(" ")[0] }
                            .map {
                                "${it.dt_txt}: ${it.main.temp.toInt()}°C, ${it.weather.firstOrNull()?.description}"
                            }
                    } else {
                        // Группировка прогноза по дням для нескольких дней
                        forecastList.groupBy { it.dt_txt.split(" ")[0] }.entries.take(days).flatMap { (date, forecasts) ->
                            val avgTemp = forecasts.map { it.main.temp }.average().toInt()
                            val descriptions = forecasts.map { it.weather.firstOrNull()?.description }.distinct().joinToString(", ")
                            listOf("$date: $avgTemp°C, $descriptions")
                        }
                    }

                    callback(forecast)
                } else {
                    Log.e("WeatherViewModel", "Ошибка: ${response.code()} ${response.message()}")
                    callback(listOf("Ошибка загрузки прогноза"))
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Исключение при запросе прогноза", e)
                callback(listOf("Ошибка соединения"))
            }
        }
    }
}
