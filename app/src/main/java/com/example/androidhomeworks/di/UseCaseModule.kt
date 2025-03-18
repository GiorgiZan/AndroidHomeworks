package com.example.androidhomeworks.di

import com.example.androidhomeworks.domain.usecase.GetEquipmentUseCase
import com.example.androidhomeworks.domain.usecase.GetEquipmentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindClearLoginInfoUseCase(useCase: GetEquipmentUseCaseImpl):GetEquipmentUseCase




}