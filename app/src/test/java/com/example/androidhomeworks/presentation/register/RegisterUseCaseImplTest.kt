package com.example.androidhomeworks.presentation.register

import com.example.androidhomeworks.domain.model.RegisterResponse
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCase
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterUseCaseImplTest {

    private lateinit var repository: RegisterRepository
    private lateinit var useCase: RegisterUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = RegisterUseCaseImpl(repository)
    }

    @Test
    fun `invoke returns success from repository`() = runTest {
        val email = "test@mail.com"
        val password = "Password123"
        val expectedResponse = RegisterResponse(id = 1, token = "abc123")

        coEvery { repository.register(email, password) } returns flowOf(Resource.Success(expectedResponse))

        val result = useCase(email, password).first()

        assertTrue(result is Resource.Success)
        assertEquals(expectedResponse, (result as Resource.Success).data)
    }

    @Test
    fun `invoke returns error from repository`() = runTest {
        val email = "test@mail.com"
        val password = "Password123"
        val errorMessage = "Something went wrong"

        coEvery { repository.register(email, password) } returns flowOf(Resource.Error(errorMessage))

        val result = useCase(email, password).first()

        assertTrue(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).errorMessage)
    }
}
