package com.pokemondemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pokemondemo.presentation.home.HomeScreen

internal const val menuRoute = "menu"

fun NavGraphBuilder.menuListScreen(navController: NavHostController) {
    composable(menuRoute) {
        HomeScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name)
            },
        )
    }
}

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
){
    navigate(menuRoute, navOptions)
}