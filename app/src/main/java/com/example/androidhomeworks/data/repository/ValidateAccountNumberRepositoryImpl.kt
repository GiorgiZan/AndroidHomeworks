package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.common.ApiHelper
import com.example.androidhomeworks.domain.model.ValidateAccountNumber
import com.example.androidhomeworks.domain.repository.ValidateAccountNumberRepository
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateAccountNumberRepositoryImpl@Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) : ValidateAccountNumberRepository {
    override suspend fun validateAccount(accountNumber: String): Flow<Resource<ValidateAccountNumber>> {
        return apiHelper.handleHttpRequest {
            retrofitService.validateAccountNumber(accountNumber = accountNumber)
        }.mapResource { it.toDomain() }
    }
}