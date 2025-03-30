package com.example.androidhomeworks.di

import com.example.androidhomeworks.data.repository.GetAccountRepositoryImpl
import com.example.androidhomeworks.data.repository.GetCourseRepositoryImpl
import com.example.androidhomeworks.data.repository.ValidateAccountNumberRepositoryImpl
import com.example.androidhomeworks.domain.repository.GetAccountsRepository
import com.example.androidhomeworks.domain.repository.GetCourseRepository
import com.example.androidhomeworks.domain.repository.ValidateAccountNumberRepository
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
    abstract fun bindGetAccountRepository(
        impl: GetAccountRepositoryImpl
    ): GetAccountsRepository

    @Binds
    @Singleton
    abstract fun bindValidateAccountNumberRepository(
        impl: ValidateAccountNumberRepositoryImpl
    ): ValidateAccountNumberRepository

    @Binds
    @Singleton
    abstract fun bindGetCourseRepository(
        impl: GetCourseRepositoryImpl
    ): GetCourseRepository


}