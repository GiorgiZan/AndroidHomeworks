package com.example.androidhomeworks

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HelperMethods {
    companion object {
        fun convertTimestampToDate(timestamp: String): String {
            val date = Date(timestamp.toLong())
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return formatter.format(date)
        }
    }
}