package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.model.Equipment
import com.example.androidhomeworks.domain.repository.EquipmentRepository
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) :EquipmentRepository{
    override suspend fun getEquipment(): Flow<Resource<List<Equipment>>> {
        return apiHelper.handleHttpRequest {
            retrofitService.getEquipment()
        }.mapResource { response ->
            response.map { it.toDomain() }
        }
    }
}