package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.model.account.AccountDto
import com.example.androidhomeworks.domain.common.CardType
import com.example.androidhomeworks.domain.common.ValuteType
import com.example.androidhomeworks.domain.model.Account

fun AccountDto.toDomain(): Account {
    return Account(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = ValuteType.fromString(valuteType),
        cardType = CardType.fromString(cardType),
        balance = balance,
        cardLogo = cardLogo
    )
}