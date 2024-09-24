package com.pokemondemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import com.pokemondemo.presentation.common.BottomAppBarItem

sealed class AppDestination(val route: String) {
    object Home : AppDestination("home")
    object Menu : AppDestination("menu")
    object Details : AppDestination("details")
}

val bottomAppBarItems = listOf(
    BottomAppBarItem(
        label = "Home",
        icon = Icons.Filled.Home,
        destination = AppDestination.Home
    ),
    BottomAppBarItem(
        label = "Menu",
        icon = Icons.Filled.Menu,
        destination = AppDestination.Menu
    )
)