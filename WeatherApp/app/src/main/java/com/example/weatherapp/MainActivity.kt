package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var cityAdapter: CityAdapter

    private val cityList = mutableListOf("Gomel", "Yaroslavl")  // Встроенные города

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCities)
        val addCityButton: Button = findViewById(R.id.buttonAddCity)
        val editTextCity: EditText = findViewById(R.id.editTextCity)

        cityAdapter = CityAdapter(mutableListOf()) { city ->
            openDetailActivity(city)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cityAdapter

        // Загрузка погоды для встроенных городов
        cityList.forEach { city ->
            loadWeather(city)
        }

        // Обработка добавления нового города
        addCityButton.setOnClickListener {
            val city = editTextCity.text.toString().trim()
            if (city.isNotEmpty()) {
                loadWeather(city)
                editTextCity.text.clear()
            }
        }
    }

    // Метод для загрузки погоды и добавления в список
    private fun loadWeather(city: String) {
        weatherViewModel.getWeather(city) { temperature ->
            runOnUiThread {
                cityAdapter.addCity(city to temperature)
            }
        }
    }

    // Открытие новой активности с подробной информацией
    private fun openDetailActivity(city: String) {
        val intent = Intent(this, CityDetailActivity::class.java).apply {
            putExtra("cityName", city)
        }
        startActivity(intent)
    }

    private fun openForecastActivity(city: String, forecastList: List<String>) {
        val intent = Intent(this, ForecastActivity::class.java).apply {
            putExtra("city_name", city)
            putStringArrayListExtra("forecast_list", ArrayList(forecastList))
        }
        startActivity(intent)
    }

}
