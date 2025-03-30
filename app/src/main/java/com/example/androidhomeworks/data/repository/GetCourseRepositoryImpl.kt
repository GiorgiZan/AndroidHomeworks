package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.data.remote.retrofit.BeeceptorService
import com.example.androidhomeworks.domain.common.ApiHelper
import com.example.androidhomeworks.domain.model.Course
import com.example.androidhomeworks.domain.repository.GetCourseRepository
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCourseRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val beeceptorService: BeeceptorService
) : GetCourseRepository {
    override suspend fun getExchangeRate(
        fromAccount: String,
        toAccount: String
    ): Flow<Resource<Course>> {
        return apiHelper.handleHttpRequest {
            beeceptorService.getExchangeRate(fromAccount,toAccount)
        }.mapResource {  it.toDomain()}
    }
}