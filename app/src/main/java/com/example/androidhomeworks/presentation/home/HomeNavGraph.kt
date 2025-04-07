package com.example.androidhomeworks.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeNavGraph(
    onNavigateToProfile: () -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToProfile = onNavigateToProfile
        )
    }
}