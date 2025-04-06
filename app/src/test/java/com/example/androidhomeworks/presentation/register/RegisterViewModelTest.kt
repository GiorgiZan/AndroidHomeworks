package com.example.androidhomeworks.presentation.register

import com.example.androidhomeworks.domain.usecase.register.RegisterUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCaseImpl
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCaseImpl
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class RegisterViewModelTest {

    private lateinit var viewModel: RegisterViewModel

    private val registerUseCase: RegisterUseCase = mockk()
    private val emailValidationUseCase: EmailValidationUseCase = mockk()
    private val passwordValidationUseCase: PasswordValidationUseCase = mockk()
    private val repeatPasswordValidationUseCase: RepeatPasswordValidationUseCase = mockk()

    @Before
    fun setup() {
        viewModel = RegisterViewModel(
            registerUseCase,
            emailValidationUseCase,
            passwordValidationUseCase,
            repeatPasswordValidationUseCase
        )
    }

    @Test
    fun `onEmailChanged updates state with valid email`() = runTest {
        every { emailValidationUseCase("test@mail.com") } returns true

        viewModel.onEvent(RegisterUiEvent.OnEmailChanged("test@mail.com"))

        val state = viewModel.uiState.first()
        assertEquals("test@mail.com", state.email)
        assertTrue(state.isEmailValid)
    }

    @Test
    fun `valid email passes validation`() = runTest {
        // Given
        val validEmail = "test@mail.com"
        val emailValidationUseCase = EmailValidationUseCaseImpl()

        // When
        val result = emailValidationUseCase(validEmail)

        // Then
        assertTrue(result)
    }

    @Test
    fun `invalid email fails validation`() = runTest {
        // Given
        val invalidEmail = "testmailcom"
        val emailValidationUseCase = EmailValidationUseCaseImpl()

        // When
        val result = emailValidationUseCase(invalidEmail)

        // Then
        assertFalse(result)
    }


    @Test
    fun `onPasswordChanged updates state with valid password`() = runTest {
        every { passwordValidationUseCase("StrongPass123") } returns true

        viewModel.onEvent(RegisterUiEvent.OnPasswordChanged("StrongPass123"))

        val state = viewModel.uiState.first()
        assertEquals("StrongPass123", state.password)
        assertTrue(state.isPasswordValid)
    }

    @Test
    fun `valid password passes validation`() = runTest {
        // Given
        val validPassword = "Pass123"
        val passwordValidationUseCase = PasswordValidationUseCaseImpl()

        // When
        val result = passwordValidationUseCase(validPassword)

        // Then
        assertTrue(result)
    }

    @Test
    fun `invalid password fails validation`() = runTest {
        // Given
        val invalidPassword = ""
        val passwordValidationUseCase = PasswordValidationUseCaseImpl()

        // When
        val result = passwordValidationUseCase(invalidPassword)

        // Then
        assertFalse(result)
    }

    @Test
    fun `onRepeatedPasswordChanged updates state with valid repeated password`() = runTest {
        every { repeatPasswordValidationUseCase("StrongPass123", "StrongPass123") } returns true

        viewModel.onEvent(
            RegisterUiEvent.OnRepeatedPasswordChanged(
                password = "StrongPass123",
                repeatedPassword = "StrongPass123"
            )
        )

        val state = viewModel.uiState.first()
        assertEquals("StrongPass123", state.repeatedPassword)
        assertTrue(state.isRepeatedPasswordValid)
    }

    @Test
    fun `valid repeated password passes validation`() = runTest {
        // Given
        val password = "Pass123"
        val repeatedPassword = "Pass123"
        val repeatPasswordValidationUseCase = RepeatPasswordValidationUseCaseImpl()

        // When
        val result = repeatPasswordValidationUseCase(password, repeatedPassword)

        // Then
        assertTrue(result)
    }

    @Test
    fun `invalid repeated password fails validation`() = runTest {
        // Given
        val password = "Pass123"
        val repeatedPassword = "Pass321"
        val repeatPasswordValidationUseCase = RepeatPasswordValidationUseCaseImpl()

        // When
        val result = repeatPasswordValidationUseCase(password, repeatedPassword)

        // Then
        assertFalse(result)
    }


}
