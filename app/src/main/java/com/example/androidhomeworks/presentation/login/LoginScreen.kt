package com.example.androidhomeworks.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidhomeworks.R
import com.example.androidhomeworks.presentation.components.CustomOutlinedTextField
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidhomeworks.presentation.components.CollectSideEffect

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    CollectSideEffect( viewModel.uiEffect){effect ->
        when (effect) {
            LoginUiEffect.NavigateToHomeScreen -> {
                onNavigateToHome()
            }

            is LoginUiEffect.ShowErrorSnackBar -> snackBarHostState.showSnackbar(effect.message)
        }
    }



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.circle_top),
            contentDescription = null,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.login_img),
                contentDescription = stringResource(R.string.login_image),
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 8.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {


            CustomOutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginUiEvent.OnEmailChanged(it)) },
                label = stringResource(R.string.email)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginUiEvent.OnPasswordChanged(it)) },
                label = stringResource(R.string.password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.rememberMe,
                    onCheckedChange = { viewModel.onEvent(LoginUiEvent.OnRememberMeChanged(it)) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(text = stringResource(R.string.remember_me))
            }

            Button(
                onClick = {
                    viewModel.onEvent(
                        LoginUiEvent.Login(
                            state.email,
                            state.password,
                            state.rememberMe
                        )
                    )
                },
                enabled = state.isEmailValid && state.isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.login))
            }
            Button(
                onClick = { onNavigateToRegister() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.register))
            }

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
