package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.marveldemo.presentation.heroes.HeroesScreen

internal const val heroesRoute = "heroes"

fun NavGraphBuilder.menuListScreen(navController: NavHostController) {
    composable(heroesRoute) {
        HeroesScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name, details.imageUrl)
            },
        )
    }
}

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
){
    navigate(heroesRoute, navOptions)
}