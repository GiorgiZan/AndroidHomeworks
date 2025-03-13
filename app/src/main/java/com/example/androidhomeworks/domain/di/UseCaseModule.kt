package com.example.androidhomeworks.domain.di

import com.example.androidhomeworks.domain.usecase.datastore.ClearLoginInfoUseCase
import com.example.androidhomeworks.domain.usecase.datastore.ClearLoginInfoUseCaseImpl
import com.example.androidhomeworks.domain.usecase.datastore.ClearSessionEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.ClearSessionEmailUseCaseImpl
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCaseImpl
import com.example.androidhomeworks.domain.usecase.datastore.GetSessionEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetSessionEmailUseCaseImpl
import com.example.androidhomeworks.domain.usecase.login.LoginUseCase
import com.example.androidhomeworks.domain.usecase.login.LoginUseCaseImpl
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCase
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCaseImpl
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCaseImpl
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCaseImpl
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCaseImpl
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
    fun bindClearLoginInfoUseCase(useCase: ClearLoginInfoUseCaseImpl):ClearLoginInfoUseCase

    @Binds
    @Singleton
    fun bindClearSessionEmailUseCase(useCase: ClearSessionEmailUseCaseImpl):ClearSessionEmailUseCase

    @Binds
    @Singleton
    fun bindGetEmailUseCase(useCase: GetEmailUseCaseImpl):GetEmailUseCase

    @Binds
    @Singleton
    fun bindGetSessionEmailUseCase(useCase: GetSessionEmailUseCaseImpl):GetSessionEmailUseCase

    @Binds
    @Singleton
    fun bindLoginUseCase(useCase: LoginUseCaseImpl):LoginUseCase

    @Binds
    @Singleton
    fun bindRegisterUseCase(useCase: RegisterUseCaseImpl):RegisterUseCase

    @Binds
    @Singleton
    fun bindEmailValidationUseCase (useCase: EmailValidationUseCaseImpl):EmailValidationUseCase

    @Binds
    @Singleton
    fun bindPasswordValidationUseCase(useCase: PasswordValidationUseCaseImpl):PasswordValidationUseCase

    @Binds
    @Singleton
    fun bindRepeatPasswordValidationUseCase(useCase: RepeatPasswordValidationUseCaseImpl): RepeatPasswordValidationUseCase

}