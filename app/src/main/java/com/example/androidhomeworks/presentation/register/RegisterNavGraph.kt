package com.example.androidhomeworks.presentation.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RegisterRoute

fun NavGraphBuilder.registerNavGraph(
    onNavigateToLogin: () -> Unit,
    onNavigateBack: () -> Unit
) {
    composable<RegisterRoute> {
        RegisterScreen(
            onNavigateToLogin = onNavigateToLogin,
            navigateBack = onNavigateBack
        )
    }
}