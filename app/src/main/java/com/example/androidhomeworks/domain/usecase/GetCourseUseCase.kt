package com.example.androidhomeworks.domain.usecase

import com.example.androidhomeworks.domain.model.Course
import com.example.androidhomeworks.domain.repository.GetCourseRepository
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCourseUseCase @Inject constructor(
    private val getCourseRepository: GetCourseRepository
) {

    suspend operator fun invoke(fromAccount: String, toAccount: String): Flow<Resource<Course>> {
        return getCourseRepository.getExchangeRate(fromAccount, toAccount)
    }
}