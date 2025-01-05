package com.example.androidhomeworks

import java.util.UUID

data class Message(
    val id:UUID,
    val text:String,
    val date: String
) {

}