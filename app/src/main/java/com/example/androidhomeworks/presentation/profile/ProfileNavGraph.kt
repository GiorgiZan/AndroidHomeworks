package com.example.androidhomeworks.presentation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

fun NavGraphBuilder.profileNavGraph(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onNavigateBack = onNavigateBack,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}