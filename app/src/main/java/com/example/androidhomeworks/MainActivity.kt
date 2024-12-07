package com.example.androidhomeworks

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberToTextConverter:NumberToTextConverter = NumberToTextConverter()

        val etNumberField:EditText = findViewById(R.id.etNumbersField)
        val btnConvertNumToWord: Button = findViewById(R.id.btnConvertNumToWord)
        val tvConvertedNumber: TextView = findViewById(R.id.tvConvertedNumber)
        val tbLanguageToggle :ToggleButton = findViewById(R.id.tbLanguageToggle)

        var emptyMessage:String
        var outOfBoundsMessage:String

        tbLanguageToggle.setOnClickListener{
            tvConvertedNumber.text = ""
            if (tbLanguageToggle.isChecked){
                etNumberField.hint = resources.getString(R.string.enter_num_hint_en)
                btnConvertNumToWord.text = resources.getString(R.string.convert_num_to_word_en)
                Toast.makeText(this, R.string.language_to_english, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                etNumberField.hint = resources.getString(R.string.enter_num_hint_ge)
                btnConvertNumToWord.text =  resources.getString(R.string.convert_num_to_word_ge)
                Toast.makeText(this, R.string.language_to_georgian, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        btnConvertNumToWord.setOnClickListener{
            val input = etNumberField.text.toString()
            val number = input.toIntOrNull()

            if (tbLanguageToggle.isChecked){
                emptyMessage = resources.getString(R.string.empty_message_en)
                outOfBoundsMessage = resources.getString(R.string.out_of_bounds_message_en)
            } else {
                emptyMessage = resources.getString(R.string.empty_message_ge)
                outOfBoundsMessage = resources.getString(R.string.out_of_bounds_message_ge)
            }



            if (input.isEmpty()) {
                Toast.makeText(this, emptyMessage, Toast.LENGTH_SHORT).show()
                tvConvertedNumber.text = ""
                return@setOnClickListener
            }
            if (number == null || number < 1 || number > 1000) {
                tvConvertedNumber.text = ""
                Toast.makeText(this, outOfBoundsMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!tbLanguageToggle.isChecked){
                val numbersIntoGeorgianText = numberToTextConverter.convertNumberToGeorgianText(number)
                tvConvertedNumber.text = numbersIntoGeorgianText
            }
            else{
                val numbersIntoEnglishText = numberToTextConverter.convertNumberToEnglishText(number)
                tvConvertedNumber.text = numbersIntoEnglishText
            }
            etNumberField.text.clear()
        }

    }
}
