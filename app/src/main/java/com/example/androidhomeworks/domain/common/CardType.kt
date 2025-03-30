package com.example.androidhomeworks.domain.common

enum class CardType {
    VISA,
    MASTER_CARD;

    companion object {
        fun fromString(value: String): CardType {
            return when (value.uppercase()) {
                "VISA" -> VISA
                "MASTER_CARD" -> MASTER_CARD
                else -> throw IllegalArgumentException("Unknown card type: $value")
            }
        }
    }
}
