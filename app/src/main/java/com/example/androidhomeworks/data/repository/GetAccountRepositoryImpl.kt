package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.common.ApiHelper
import com.example.androidhomeworks.domain.model.Account
import com.example.androidhomeworks.domain.repository.GetAccountsRepository
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
): GetAccountsRepository {
    override suspend fun getAccount(): Flow<Resource<List<Account>>> {
        return apiHelper.handleHttpRequest {
            retrofitService.getAccount()
        }.mapResource { response -> response.map { it.toDomain() } }
    }
}