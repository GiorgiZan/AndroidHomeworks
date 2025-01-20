package com.example.androidhomeworks

enum class LastMessageEnum(val type:String) {
    TEXT("text"),
    FILE("file"),
    VOICE("voice");

    companion object {
        fun fromString(value: String): LastMessageEnum {
            return entries.firstOrNull { it.type == value } ?: TEXT
        }
    }

}