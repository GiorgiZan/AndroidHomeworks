package com.example.androidhomeworks.domain.usecase

import com.example.androidhomeworks.domain.model.Equipment
import com.example.androidhomeworks.domain.repository.EquipmentRepository
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetEquipmentUseCase {
    suspend operator fun invoke(filter: String?): Flow<Resource<List<Equipment>>>
}

class GetEquipmentUseCaseImpl @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) : GetEquipmentUseCase{
    override suspend fun invoke(filter: String?): Flow<Resource<List<Equipment>>> {
        return equipmentRepository.getEquipment()
            .map { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val filteredList = if (filter.isNullOrEmpty()) {
                            resource.data
                        } else {
                            resource.data.filter { it.name.contains(filter, ignoreCase = true) }
                        }

                        Resource.Success(filteredList)
                    }
                    is Resource.Error -> resource
                    is Resource.Loading -> resource
                }
            }
    }

}