package com.example.androidhomeworks.presentation.mapper

import com.example.androidhomeworks.domain.model.Account
import com.example.androidhomeworks.presentation.model.UiAccount

fun Account.toPresenter(): UiAccount {
    return UiAccount(
        id,
        accountName,
        accountNumber,
        valuteType,
        cardType,
        balance,
        cardLogo
    )
}