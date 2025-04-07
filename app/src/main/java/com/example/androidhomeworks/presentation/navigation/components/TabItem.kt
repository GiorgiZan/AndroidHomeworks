package com.example.androidhomeworks.presentation.navigation.components

import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.reflect.KClass

data class TabItem(
    val routeClass: KClass<*>,
    val icon: ImageVector,
    val label: String
){
    val route: String
        get() = routeClass.qualifiedName ?: ""
}


