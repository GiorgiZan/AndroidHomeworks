package com.example.androidhomeworks.domain.usecase

import com.example.androidhomeworks.domain.model.Account
import com.example.androidhomeworks.domain.repository.GetAccountsRepository
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountUseCase@Inject constructor(
    private val getAccountsRepository: GetAccountsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Account>>> {
        return getAccountsRepository.getAccount()
    }
}