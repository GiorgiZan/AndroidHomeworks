package com.example.androidhomeworks.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidhomeworks.R
import com.example.androidhomeworks.presentation.components.BackIcon
import com.example.androidhomeworks.presentation.components.ButtonComponent
import com.example.androidhomeworks.presentation.components.CollectSideEffect
import com.example.androidhomeworks.presentation.components.TextFieldComponent

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    CollectSideEffect(viewModel.uiEffect) { effect ->
        when (effect) {
            RegisterUiEffect.NavigateToLogin -> onNavigateToLogin()
            is RegisterUiEffect.ShowErrorSnackBar -> snackBarHostState.showSnackbar(effect.message)
        }
    }
    RegisterContent(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::onEvent,
        scrollState = scrollState,
        navigateBack = navigateBack
    )
}

@Composable
fun RegisterContent(
    state: RegisterUiState,
    snackBarHostState: SnackbarHostState,
    onEvent: (RegisterUiEvent) -> Unit,
    scrollState: ScrollState,
    navigateBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.circle_top),
            contentDescription = null,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
        ) {
            BackIcon(
                onBack = navigateBack,
                modifier = Modifier
                    .padding(top = 20.dp, start = 6.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.register),
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.register_img),
                contentDescription = stringResource(R.string.register),
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                value = state.email,
                onValueChange = { onEvent(RegisterUiEvent.OnEmailChanged(it)) },
                label = stringResource(R.string.email),
                leadingIcon = Icons.Default.Mail
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                value = state.password,
                onValueChange = { onEvent(RegisterUiEvent.OnPasswordChanged(it)) },
                label = stringResource(R.string.password),
                isPassword = true,
                leadingIcon = Icons.Default.Lock
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                value = state.repeatedPassword,
                onValueChange = {
                    onEvent(
                        RegisterUiEvent.OnRepeatedPasswordChanged(
                            state.password,
                            it
                        )
                    )
                },
                label = stringResource(R.string.password),
                isPassword = true,
                leadingIcon = Icons.Default.Lock
            )

            Spacer(modifier = Modifier.height(24.dp))



            ButtonComponent(
                text = stringResource(R.string.register),
                onClick = {
                    onEvent(
                        RegisterUiEvent.Register(
                            state.email,
                            state.password,
                            state.repeatedPassword
                        )
                    )
                },
                enabled = state.isEmailValid && state.isPasswordValid && state.isRepeatedPasswordValid
            )


        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        Image(
            painter = painterResource(id = R.drawable.circle_bottom),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomStart),
        )

        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(enabled = false) { }
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}