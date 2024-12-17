package com.example.androidhomeworks

import android.content.Context
import android.widget.EditText

class UserFieldsValidation(private val context:Context) {
    fun userFirstNameValidation(firstName: EditText): Boolean {
        if (firstName.text.toString().trim().isEmpty()){
            firstName.error = context.getString(R.string.first_name_empty)
            return false
        }
        if (firstName.text.toString().trim().length > 20){
            firstName.error = context.getString(R.string.first_name_length)
            return false
        }

        return true
    }

    fun userLastNameValidation(lastName: EditText):Boolean{
        if (lastName.text.toString().trim().isEmpty()){
            lastName.error = context.getString(R.string.last_name_empty)
            return false
        }
        if (lastName.text.toString().trim().length > 20){
            lastName.error = context.getString(R.string.last_name_length)
            return false
        }

        return true
    }

    fun userAgeValidation(age: EditText) : Boolean{
        if (age.text.toString().trim().isEmpty()){
            age.error = context.getString(R.string.age_should_empty)
            return false
        }
        if (age.text.toString().trim().toInt() == 0){
            age.error = context.getString(R.string.age_zero)
            return false
        }

        if (age.text.toString().trim().toInt() > 100){
            age.error = context.getString(R.string.age_more_than_hundred)
            return false
        }
        return true
    }

    fun userEmailValidations(email: EditText): Boolean{
        if (email.text.toString().trim().isEmpty()){
            email.error = context.getString(R.string.email_empty)
            return false
        }
        else if (!email.text.toString().trim().contains(context.getString(R.string.email_symbol))){
            email.error = context.getString(R.string.email_invalid)
            return false
        }
        else if (email.text.toString().trim().length < 7 || email.text.toString().trim().length >= 35 ){
            email.error = context.getString(R.string.email_length)
            return false
        }

        return true
    }
}