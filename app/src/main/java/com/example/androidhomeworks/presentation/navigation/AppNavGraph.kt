package com.example.androidhomeworks.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidhomeworks.presentation.home.HomeRoute
import com.example.androidhomeworks.presentation.home.homeNavGraph
import com.example.androidhomeworks.presentation.login.LoginRoute
import com.example.androidhomeworks.presentation.login.loginNavGraph
import com.example.androidhomeworks.presentation.navigation.components.TabBar
import com.example.androidhomeworks.presentation.profile.ProfileRoute
import com.example.androidhomeworks.presentation.profile.profileNavGraph
import com.example.androidhomeworks.presentation.register.RegisterRoute
import com.example.androidhomeworks.presentation.register.registerNavGraph


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

            loginNavGraph(
                onNavigateToRegister = { navController.navigate(RegisterRoute) },
                onNavigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )

            registerNavGraph(
                onNavigateToLogin = { navController.navigate(LoginRoute) },
                onNavigateBack = { navController.navigateUp() }
            )

            homeNavGraph(
                onNavigateToProfile = { navController.navigate(ProfileRoute) }
            )

            profileNavGraph(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToLogin = {
                    navController.navigate(LoginRoute) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
