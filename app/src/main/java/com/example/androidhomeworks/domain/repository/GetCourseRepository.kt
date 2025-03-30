package com.example.androidhomeworks.domain.repository

import com.example.androidhomeworks.domain.model.Course
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface GetCourseRepository {
    suspend fun getExchangeRate(fromAccount: String, toAccount: String): Flow<Resource<Course>>
}