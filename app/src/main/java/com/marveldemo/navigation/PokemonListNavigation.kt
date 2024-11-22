package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.marveldemo.presentation.home.HomeScreen

internal const val marvelHomeRoute = "marvelHome"

fun NavGraphBuilder.pokemonListScreen(navController: NavHostController) {
    composable(marvelHomeRoute) {
        HomeScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name, details.name)
            },
        )
    }
}

fun NavController.navigateToPokemonList(
    navOptions: NavOptions? = null
) {
    navigate(marvelHomeRoute, navOptions)
}