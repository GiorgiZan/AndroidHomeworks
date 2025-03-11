package com.example.androidhomeworks.data.di

import com.example.androidhomeworks.data.repository.DataStoreRepositoryImpl
import com.example.androidhomeworks.data.repository.LoginRepositoryImpl
import com.example.androidhomeworks.data.repository.RegisterRepositoryImpl
import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import com.example.androidhomeworks.domain.repository.login.LoginRepository
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
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
        impl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository
}