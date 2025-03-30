package com.example.androidhomeworks.presentation.screens.transfer

sealed class TransferEvent {
    data object FetchAccountEvent : TransferEvent()
    data class ValidateAccountNumber(val accountNumber:String) : TransferEvent()
    data class GetCourse(val fromAccount: String, val toAccount: String) : TransferEvent()
}