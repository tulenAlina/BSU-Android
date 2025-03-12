package com.example.calculator14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.PI
import kotlin.math.pow

class SectorAreaFragment : Fragment() {

    private lateinit var editTextL: EditText
    private lateinit var editTextA: EditText
    private lateinit var textViewResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sector_area, container, false)

        editTextL = view.findViewById(R.id.editTextL)
        editTextA = view.findViewById(R.id.editTextA)
        textViewResult = view.findViewById(R.id.textViewResult)

        view.findViewById<Button>(R.id.buttonCalculate).setOnClickListener {
            calculateSectorArea()
        }

        return view
    }

    private fun calculateSectorArea() {
        val L = editTextL.text.toString().toDoubleOrNull()
        val A = editTextA.text.toString().toDoubleOrNull()

        if (L == null || A == null || A == 0.0) {
            Toast.makeText(requireContext(), "Некорректный ввод", Toast.LENGTH_SHORT).show()
            return
        }

        val radius = L / (A * PI / 180)
        val area = 0.5 * radius.pow(2) * A * PI / 180
        textViewResult.text = "Площадь сектора: $area"
    }
}
