package com.example.androidhomeworks.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData(
        listOf(
            Card(
                UUID.randomUUID(),
                CardType.MASTERCARD,
                2345421321346531,
                "Joseph Stalin",
                "12/29",
                161
            ),
            Card(
                UUID.randomUUID(),
                CardType.VISA,
                1115829204123214,
                "Alexandre the great",
                "10/24",
                321
            )
        )
    )

    val cards get() = _cards


    fun addCard(newCard: Card) {
        val cards = _cards.value.orEmpty().toMutableList()
        cards.add(0, newCard)
        _cards.value = cards
    }

    fun deleteCardById(id: UUID) {
        val cards = _cards.value.orEmpty().toMutableList()
        cards.removeAll { it.id == id }
        _cards.value = cards
    }
}