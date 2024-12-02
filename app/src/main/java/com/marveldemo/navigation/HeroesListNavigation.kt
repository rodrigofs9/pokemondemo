package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.marveldemo.presentation.heroes.HeroesScreen

internal const val marvelHeroesRoute = "marvelHeroes"

fun NavGraphBuilder.heroesListScreen(navController: NavHostController) {
    composable(marvelHeroesRoute) {
        HeroesScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name)
            },
        )
    }
}

fun NavController.navigateToHeroesList(
    navOptions: NavOptions? = null
) {
    navigate(marvelHeroesRoute, navOptions)
}