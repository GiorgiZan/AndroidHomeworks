package com.example.androidhomeworks.cards

import java.util.UUID

data class Card(
    val id: UUID,
    val cardType: CardType,
    val cardNo: Long,
    val cardHolderName: String,
    val cardValidThru: String,
    val cardCvv: Int
) {
}