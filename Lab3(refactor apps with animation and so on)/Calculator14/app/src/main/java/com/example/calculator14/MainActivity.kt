package com.example.calculator14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_calculator -> loadFragment(CalculatorFragment())
                R.id.nav_sector_area -> loadFragment(SectorAreaFragment())
                R.id.nav_settings -> loadFragment(SettingsFragment())
            }
            true
        }

        // Загружаем калькулятор при запуске
        if (savedInstanceState == null) {
            loadFragment(CalculatorFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
