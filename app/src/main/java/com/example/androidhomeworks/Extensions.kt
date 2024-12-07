package com.example.androidhomeworks


fun Int.firstDigit(): Int {
    val firstDigit = this.toString().first().toString().toInt()
    return firstDigit
}