package com.example.androidhomeworks.data.repository.login

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.data.preference_key.PreferenceKeys
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.model.LoginResponse
import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import com.example.androidhomeworks.domain.repository.login.LoginRepository
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService,
    private val dataStoreRepository: DataStoreRepository

) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean
    ): Flow<Resource<LoginResponse>> =
        apiHelper.handleHttpRequest {
            retrofitService.login(email, password)
        }.onEach { result ->
            if (result is Resource.Success) {
                dataStoreRepository.saveValue(PreferenceKeys.EMAIL, email)
                dataStoreRepository.saveValue(PreferenceKeys.REMEMBER_ME, rememberMe)
            }
        }.mapResource { it.toDomain() }
}