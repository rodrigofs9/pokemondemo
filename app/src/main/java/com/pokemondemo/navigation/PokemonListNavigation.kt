package com.pokemondemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pokemondemo.presentation.home.HomeScreen

internal const val pokemonListRoute = "pokemon"

fun NavGraphBuilder.pokemonListScreen(navController: NavHostController) {
    composable(pokemonListRoute) {
        HomeScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name)
            },
        )
    }
}

fun NavController.navigateToPokemonList(
    navOptions: NavOptions? = null
) {
    navigate(pokemonListRoute, navOptions)
}