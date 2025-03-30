package com.example.androidhomeworks.presentation.model

import com.example.androidhomeworks.domain.common.CardType
import com.example.androidhomeworks.domain.common.ValuteType

data class UiAccount(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: ValuteType,
    val cardType: CardType,
    val balance: Int,
    val cardLogo: String?
)

