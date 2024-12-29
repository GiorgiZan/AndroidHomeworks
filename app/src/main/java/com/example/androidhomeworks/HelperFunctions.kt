package com.example.androidhomeworks

import java.text.SimpleDateFormat
import java.util.Date

class HelperFunctions {
    companion object {
        fun convertMillisecondsToDate(milliseconds: Long): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val dateString = formatter.format(Date(milliseconds))
            return dateString
        }
    }
}