package com.example.androidhomeworks.di

import com.example.androidhomeworks.data.repository.UploadImageRepositoryImpl
import com.example.androidhomeworks.domain.repository.UploadImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(repository: UploadImageRepositoryImpl): UploadImageRepository

}