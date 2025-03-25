package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(
    private val cities: MutableList<Pair<String, String>>,
    private val onCityClick: (String) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)
        private val cityTemp: TextView = itemView.findViewById(R.id.cityTemp)

        fun bind(cityData: Pair<String, String>) {
            cityName.text = cityData.first
            cityTemp.text = cityData.second
            itemView.setOnClickListener { onCityClick(cityData.first) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size

    // Метод для добавления города
    fun addCity(city: Pair<String, String>) {
        cities.add(city)
        notifyItemInserted(cities.size - 1)
    }
}
