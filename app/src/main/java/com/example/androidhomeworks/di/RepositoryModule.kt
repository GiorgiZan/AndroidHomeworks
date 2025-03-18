package com.example.androidhomeworks.di

import com.example.androidhomeworks.data.repository.EquipmentRepositoryImpl
import com.example.androidhomeworks.domain.repository.EquipmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        impl: EquipmentRepositoryImpl
    ): EquipmentRepository


}