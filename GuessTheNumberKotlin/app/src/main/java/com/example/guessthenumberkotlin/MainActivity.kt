package com.example.guessthenumberkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guessthenumberkotlin.R
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvInfo: TextView
    private lateinit var etInput: EditText
    private lateinit var bControl: Button
    private var randomNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.textView2)
        etInput = findViewById(R.id.editText)
        bControl = findViewById(R.id.button1)

        generateRandomNumber()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun generateRandomNumber() {
        randomNumber = Random().nextInt(100) + 1
        tvInfo.text = getString(R.string.try_to_guess)
    }

    fun onClick(view: android.view.View) {
        try {
            val inputValue = etInput.text.toString().toInt()

            when {
                inputValue < 1 || inputValue > 100 -> {
                    tvInfo.text = getString(R.string.error)
                }
                inputValue == randomNumber -> {
                    tvInfo.text = getString(R.string.hit)
                }
                inputValue < randomNumber -> {
                    tvInfo.text = getString(R.string.behind)
                }
                else -> {
                    tvInfo.text = getString(R.string.ahead)
                }
            }
        } catch (e: NumberFormatException) {
            tvInfo.text = getString(R.string.error)
            Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show()
        }
    }

    fun onExitClick(view: android.view.View) {
        finish()
    }

    fun onNewGameClick(view: android.view.View) {
        generateRandomNumber()
        etInput.text.clear()
        tvInfo.text = getString(R.string.try_to_guess)
    }
}