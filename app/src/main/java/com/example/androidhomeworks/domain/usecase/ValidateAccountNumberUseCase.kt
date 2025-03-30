package com.example.androidhomeworks.domain.usecase

import com.example.androidhomeworks.domain.model.ValidateAccountNumber
import com.example.androidhomeworks.domain.repository.ValidateAccountNumberRepository
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateAccountNumberUseCase @Inject constructor(
    private val validateAccountNumberRepository: ValidateAccountNumberRepository
) {
    suspend operator fun invoke(accountNumber: String) : Flow<Resource<ValidateAccountNumber>> {
        return validateAccountNumberRepository.validateAccount(accountNumber)
    }
}