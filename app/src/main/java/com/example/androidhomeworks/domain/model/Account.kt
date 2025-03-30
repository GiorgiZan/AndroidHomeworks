package com.example.androidhomeworks.domain.model

import com.example.androidhomeworks.domain.common.CardType
import com.example.androidhomeworks.domain.common.ValuteType


data class Account(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: ValuteType,
    val cardType: CardType,
    val balance: Int,
    val cardLogo: String?
)

