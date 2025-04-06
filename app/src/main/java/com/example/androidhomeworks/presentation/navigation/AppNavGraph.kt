package com.example.androidhomeworks.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidhomeworks.presentation.home.HomeScreen
import com.example.androidhomeworks.presentation.login.LoginScreen
import com.example.androidhomeworks.presentation.profile.ProfileScreen
import com.example.androidhomeworks.presentation.register.RegisterScreen
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute


@Serializable
data object HomeRoute

@Serializable
data object ProfileRoute


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = Color.Unspecified,
        bottomBar = {
            if (currentDestination == HomeRoute::class.qualifiedName ||
                currentDestination == ProfileRoute::class.qualifiedName
            ) {
                TabBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<LoginRoute> {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate(RegisterRoute) },
                    onNavigateToHome = {
                        navController.navigate(route = HomeRoute, builder = {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        })
                    }
                )
            }
            composable<RegisterRoute> {
                RegisterScreen(
                    onNavigateToLogin = { navController.navigate(LoginRoute) },
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable<HomeRoute> {
                HomeScreen(
                    onNavigateToProfile = { navController.navigate(ProfileRoute) }
                )
            }
            composable<ProfileRoute> {
                ProfileScreen(
                    onNavigateBack = { navController.navigateUp() },
                    onNavigateToLogin = { navController.navigate(route = LoginRoute, builder = {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }) }
                )
            }
        }
    }
}
