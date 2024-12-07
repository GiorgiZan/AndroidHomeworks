package com.example.androidsecondhomework

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val enterProfileInfoLinearLayout:LinearLayoutCompat = findViewById(R.id.enterProfileInfoLinearLayout)
        val etEmail:AppCompatEditText = findViewById(R.id.etEmail)
        val etUsername:AppCompatEditText = findViewById(R.id.etUsername)
        val etFirstName:AppCompatEditText = findViewById(R.id.etFirstName)
        val etLastName:AppCompatEditText = findViewById(R.id.etLastName)
        val etAge:AppCompatEditText = findViewById(R.id.etAge)
        val btnSave:AppCompatButton = findViewById(R.id.btnSave)
        val btnClear:AppCompatButton = findViewById(R.id.btnClear)


        val displayProfileInfoLinearLayout:LinearLayoutCompat = findViewById(R.id.displayProfileInfoLinearLayout)
        val tvEmail:AppCompatTextView = findViewById(R.id.tvEmail)
        val tvUsername:AppCompatTextView = findViewById(R.id.tvUsername)
        val tvFirstNameAndLastName:AppCompatTextView = findViewById(R.id.tvFirstNameAndLastName)
        val tvAge:AppCompatTextView = findViewById(R.id.tvAge)
        val btnAgain:AppCompatButton= findViewById(R.id.btnAgain)

        btnSave.setOnClickListener{
            if (etEmail.text!!.isEmpty()) {
                etEmail.error = resources.getString(R.string.email_empty)
                return@setOnClickListener
            }
            else if(!etEmail.text!!.toString().contains("@") ){
                etEmail.error = resources.getString(R.string.email_not_valid)
                return@setOnClickListener
            }
            else if (etEmail.text!!.length < 7 || etEmail.text!!.length >= 35){
                etEmail.error = resources.getString(R.string.email_length_not_valid)
                return@setOnClickListener
            }

            if (etUsername.text!!.isEmpty()){
                etUsername.error = resources.getString(R.string.username_empty)
                return@setOnClickListener
            }

            else if(etUsername.text!!.length < 10 || etUsername.text!!.length >= 35 ){
                etUsername.error = resources.getString(R.string.username_length_not_valid)
                return@setOnClickListener
            }

            if (etFirstName.text!!.isEmpty()){
                etFirstName.error = resources.getString(R.string.first_name_empty)
                return@setOnClickListener
            }
            else if (etFirstName.text!!.length >= 25){
                etFirstName.error = resources.getString(R.string.first_name_too_long)
                return@setOnClickListener
            }

            if (etLastName.text!!.isEmpty()){
                etLastName.error = resources.getString(R.string.last_name_empty)
                return@setOnClickListener
            }

            else if (etLastName.text!!.length >= 25){
                etLastName.error = resources.getString(R.string.last_name_too_long)
                return@setOnClickListener
            }

            if (etAge.text!!.isEmpty()){
                etAge.error = resources.getString(R.string.age_empty)
                return@setOnClickListener
            }

            else if (etAge.text!!.toString().toInt() == 0 || etAge.text!!.toString().toInt() > 100){
                etAge.error = resources.getString(R.string.age_not_valid)
                return@setOnClickListener
            }

            tvEmail.text = "Email: ${etEmail.text}"
            tvUsername.text =  "Username:  ${etUsername.text}"
            tvFirstNameAndLastName.text = "Full Nmae: ${etFirstName.text}  ${etLastName.text}"
            tvAge.text = "Age: ${etAge.text}"
            enterProfileInfoLinearLayout.visibility = LinearLayout.GONE
            displayProfileInfoLinearLayout.visibility = LinearLayout.VISIBLE

        }

        btnClear.setOnLongClickListener {
            clearEditTextFields(etEmail, etUsername, etFirstName, etLastName, etAge)
            return@setOnLongClickListener true
        }

        btnAgain.setOnClickListener{
            clearEditTextFields(etEmail, etUsername, etFirstName, etLastName, etAge)
            displayProfileInfoLinearLayout.visibility = LinearLayout.GONE
            enterProfileInfoLinearLayout.visibility = LinearLayout.VISIBLE
        }

    }

    private fun clearEditTextFields(vararg fields: AppCompatEditText) {
        fields.forEach{
            it.text?.clear()
        }
    }
}