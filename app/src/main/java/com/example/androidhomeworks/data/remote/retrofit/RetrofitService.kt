package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.model.account.AccountDto
import com.example.androidhomeworks.data.remote.model.account_number_validation.ValidateAccountNumberDto
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("d689fe3e-6faf-446a-9896-c538de3449fa")
    suspend fun getAccount(): Response<List<AccountDto>>

    @GET("29d002d4-3ccd-4eaa-95eb-a9d1601ce123")
    suspend fun validateAccountNumber(
        @Query("account_number") accountNumber: String
    ): Response<ValidateAccountNumberDto>

}