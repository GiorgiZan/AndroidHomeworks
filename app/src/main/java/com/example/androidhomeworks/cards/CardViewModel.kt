package com.example.androidhomeworks.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class CardViewModel : ViewModel() {
    // Use MutableLiveData to hold the list of cards
    private val _cards = MutableLiveData<List<Card>>(
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

    // Public LiveData to expose the cards list to the UI
    val cards: LiveData<List<Card>> get() = _cards

    // Function to add a card
    fun addCard(newCard: Card) {
        val currentList = _cards.value.orEmpty().toMutableList()
        currentList.add(0, newCard)  // Add card at the start
        _cards.value = currentList  // Update LiveData
    }

    // Function to delete a card by ID
    fun deleteCardById(id: UUID) {
        val currentList = _cards.value.orEmpty().toMutableList()
        currentList.removeAll { it.id == id }  // Remove card by ID
        _cards.value = currentList  // Update LiveData
    }
}