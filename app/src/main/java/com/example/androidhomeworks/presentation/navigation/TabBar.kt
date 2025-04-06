package com.example.androidhomeworks.presentation.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TabBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = currentRoute == HomeRoute::class.qualifiedName,
            onClick = {
                navController.navigate(HomeRoute) {
                    popUpTo(HomeRoute) { inclusive = false }
                    launchSingleTop = true
                }
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = currentRoute == ProfileRoute::class.qualifiedName,
            onClick = {
                navController.navigate(ProfileRoute) {
                    popUpTo(ProfileRoute) { inclusive = false }
                    launchSingleTop = true
                }
            },
            label = { Text("Profile") }
        )
    }
}
