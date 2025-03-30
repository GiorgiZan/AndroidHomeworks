package com.example.androidhomeworks.domain.repository

import com.example.androidhomeworks.domain.model.Equipment
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {
    suspend fun getEquipment(): Flow<Resource<List<Equipment>>>
}