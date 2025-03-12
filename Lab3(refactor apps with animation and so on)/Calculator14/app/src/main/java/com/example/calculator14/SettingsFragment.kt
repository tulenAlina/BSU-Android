package com.example.calculator14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBoxAnimations)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(requireContext(), "Анимации: ${if (isChecked) "Включены" else "Выключены"}", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
