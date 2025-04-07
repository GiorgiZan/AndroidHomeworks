package com.example.androidhomeworks.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

fun NavGraphBuilder.loginNavGraph(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToHome = onNavigateToHome
        )
    }
}
