package com.example.androidhomeworks.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat("d MMMM 'at' h:mma", Locale.ENGLISH)
    return format.format(date).lowercase(Locale.ENGLISH)
}
