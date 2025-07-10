package com.example.bmicalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val ageRow = findViewById<View>(R.id.ageRow)
        val ageLabel = ageRow.findViewById<TextView>(R.id.label)
        val ageunit = ageRow.findViewById<TextView>(R.id.unit)
        ageLabel.text = "Age"
        ageunit.text = "year"

        val weightRow = findViewById<View>(R.id.weightRow)
        val weightLabel = weightRow.findViewById<TextView>(R.id.label)
        val weightInput = weightRow.findViewById<EditText>(R.id.valueInput)
        val weightunit = weightRow.findViewById<TextView>(R.id.unit)
        weightLabel.text = "Weight"
        weightunit.text = "Kg"

        val heightRow = findViewById<View>(R.id.heightRow)
        val heightLabel = heightRow.findViewById<TextView>(R.id.label)
        val heightInput = heightRow.findViewById<EditText>(R.id.valueInput)
        val heightunit = heightRow.findViewById<TextView>(R.id.unit)

        heightLabel.text = "Height"
        heightunit.text = "cm"

        val resultText = findViewById<TextView>(R.id.bmiResultText)
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)
        calculateBtn.setOnClickListener{
            println("Weight text: ${weightInput.text}")
            println("Height text: ${heightInput.text}")

            val weightStr = weightInput.text.toString()
            val heightStr = heightInput.text.toString()
            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()){
                var weight = weightStr.toDouble()
                var height = heightStr.toDouble()
                height /=100

                val bmi = weight / (height * height)

                val bmiFormatted = String.format("%.2f", bmi)
                val bmiCategory = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 25 -> "Normal"
                    bmi < 30 -> "Overweight"
                    else -> "Obese"
                }

                val fullText = "BMI : $bmiFormatted ($bmiCategory)"
                val spannable = SpannableString(fullText)

                val startIndex = fullText.indexOf("(")
                val endIndex = fullText.length

                spannable.setSpan(
                    RelativeSizeSpan(0.75f),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                resultText.text = spannable
                println("BMI text: ${bmiFormatted}")

            }
            else {
                resultText.text = "Please enter valid weight & height"
            }
        }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
