package com.example.androidhomeworks.domain.common

enum class ValuteType {
    GEL,
    USD,
    EUR;

    companion object {
        fun fromString(value: String): ValuteType {
            return when (value.uppercase()) {
                "GEL" -> GEL
                "USD" -> USD
                "EUR" -> EUR
                else -> throw IllegalArgumentException("Unknown valute type: $value")
            }
        }
    }
}
