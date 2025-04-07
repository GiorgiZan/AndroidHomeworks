package com.example.androidhomeworks.presentation.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidhomeworks.R
import com.example.androidhomeworks.presentation.home.HomeRoute
import com.example.androidhomeworks.presentation.profile.ProfileRoute

@Composable
fun TabBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val tabItems = listOf(
        TabItem(HomeRoute::class, Icons.Default.Home, stringResource(R.string.home)),
        TabItem(ProfileRoute::class, Icons.Default.Person, stringResource(R.string.profile))
    )

    NavigationBar {
        tabItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(item.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

